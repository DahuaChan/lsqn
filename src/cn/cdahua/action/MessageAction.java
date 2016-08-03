package cn.cdahua.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cdahua.exception.MyException;
import cn.cdahua.model.Admin;
import cn.cdahua.model.Author;
import cn.cdahua.model.Message;
import cn.cdahua.servers.IAdminServer;
import cn.cdahua.servers.IAuthorServer;
import cn.cdahua.servers.IMessageServer;
import cn.cdahua.util.ActionUtils;
import cn.cdahua.util.StringUtil;
import cn.cdahua.util.Word2htmlUtil;

@SuppressWarnings("serial")
@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport implements ModelDriven<Message> {

	private Message message;
	private IMessageServer messageServer;
	private IAdminServer adminServer;
	private IAuthorServer authorServer;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public IMessageServer getMessageServer() {
		return messageServer;
	}

	@Resource
	public void setMessageServer(IMessageServer messageServer) {
		this.messageServer = messageServer;
	}

	public IAdminServer getAdminServer() {
		return adminServer;
	}

	@Resource
	public void setAdminServer(IAdminServer adminServer) {
		this.adminServer = adminServer;
	}

	public IAuthorServer getAuthorServer() {
		return authorServer;
	}

	@Resource
	public void setAuthorServer(IAuthorServer authorServer) {
		this.authorServer = authorServer;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String add() throws Exception {
		messageServer.add(message, ((Author) ActionContext.getContext().getSession().get("author")).getA_id());
		/*
		 * 页面打印josn数据，editor.jsp执行回调函数
		 */
		String result = "{\"success\": 1}";
		ServletActionContext.getResponse().getWriter().write(result);
		return null;
	}

	public String updateInput() {
		BeanUtils.copyProperties(messageServer.load(message.getMsg_id()), message);

		return SUCCESS;
	}

	private void update() {
		Message msg = messageServer.load(message.getMsg_id());
		if (message.getMsg_title() != null && !"".equals(message.getMsg_title())) {
			msg.setMsg_content(message.getMsg_content());
			msg.setMsg_title(message.getMsg_title());
		}
		msg.setMsg_status(message.getMsg_status());
		Admin ad = ((Admin) ActionContext.getContext().getSession().get("admin"));
		if (ad != null)
			messageServer.update(msg, ad.getAdmin_id());
		else
			messageServer.update(msg, 0);
		// 判断稿件状态，是否发邮件
		if (msg.getMsg_status() != Message.MSGUNCHECK)
			adminServer.sendMail(authorServer.load(msg.getAuthor().getA_id()).getA_email(), msg.getMsg_status());
	}

	public String authorUpdate() throws IOException {
		update();
		/**
		 * UEditor 返回值为josn数据时，回调函数方起作用 commen/editor.jsp
		 */
		String result = "{\"success\": 1}";
		ServletActionContext.getResponse().getWriter().write(result);
		return null;
	}

	public String adminUpdate() {
		update();
		ActionContext.getContext().put("url", "message_list.action");
		return ActionUtils.REDIRECT;
	}

	public String delete() {
		messageServer.delete(message.getMsg_id());
		ActionContext.getContext().put("url", "message_list.action");
		return ActionUtils.REDIRECT;
	}

	// 查询所有数据
	public String list() {
		Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
		if (admin == null) {
			Author author = (Author) ActionContext.getContext().getSession().get("author");
			if (author != null) {
				message.setAuthor(author);
				ActionContext.getContext().put("msgp", messageServer.authorFindMessage(author.getA_id()));
				return SUCCESS;
			} else {
				return ActionUtils.LOGININPUT;
			}
		} else {
			message.setAdmin(admin);
			ActionContext.getContext().put("msgp", messageServer.findMessage());
			return SUCCESS;
		}
	}

	// 查询已经审核的文章
	public String listByIsRead() {
		Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
		if (admin == null) {
			Author author = (Author) ActionContext.getContext().getSession().get("author");
			if (author != null) {
				message.setAuthor(author);
				ActionContext.getContext().put("msgp", messageServer.authorFindMessageByIsRead(author.getA_id()));
				return SUCCESS;
			} else {
				return ActionUtils.LOGININPUT;
			}
		} else {
			message.setAdmin(admin);
			ActionContext.getContext().put("msgp", messageServer.findMessageByIsRead());
			return SUCCESS;
		}
	}

	// 查询未审核的文章
	public String listByUnRead() {
		Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
		if (admin == null) {
			Author author = (Author) ActionContext.getContext().getSession().get("author");
			if (author != null) {
				message.setAuthor(author);
				ActionContext.getContext().put("msgp", messageServer.authorFindMessageByUnRead(author.getA_id()));
				return SUCCESS;
			} else {
				return ActionUtils.LOGININPUT;
			}
		} else {
			message.setAdmin(admin);
			ActionContext.getContext().put("msgp", messageServer.findMessageByUnRead());
			return SUCCESS;
		}
	}

	public String view() {
		Author a = (Author) ActionContext.getContext().getSession().get("author");
		Message m = messageServer.load(message.getMsg_id());
		if (a != null) {
			if (m.getAuthor().getA_id() == a.getA_id()) {
				BeanUtils.copyProperties(m, message);
				return SUCCESS;
			}
			throw new MyException("您不是该文章的主人");
		} else {
			BeanUtils.copyProperties(m, message);
			return SUCCESS;
		}
	}

	public String readFile() throws IOException {
		List<String> lists = new ArrayList<>();
		for (int i = 0; i < ServletActionContext.getRequest().getParameterMap().size(); i++) {
			String path = URLDecoder.decode(ServletActionContext.getRequest().getParameter("path" + i), "UTF-8");
			lists.add(path.split("\\?")[1]);
		}
		// 1、生成html文件
		if(lists.size()!=0){
			Word2htmlUtil.convert2Html(lists);
		// 2、将值返回给回调函数
			String result = "{\"success\": 1}";
			ServletActionContext.getResponse().getWriter().write(result);
			return null;
		} else {
			String result = "{\"fail\": 1}";
			ServletActionContext.getResponse().getWriter().write(result);
			return null;			
		}
	}

	public String readFileList() throws IOException {
		String jsonData = URLDecoder.decode(ServletActionContext.getRequest().getParameter("jsonData"), "UTF-8");
		if(jsonData.equals("undefine"))
				throw new MyException("没有附件");
		Map<String,String> fileMap = StringUtil.readFileURLSplit(jsonData);
		ActionContext.getContext().put("filemap", fileMap);
		return SUCCESS;
	}

	@Override
	public Message getModel() {
		if (message == null)
			message = new Message();
		return message;
	}

}
