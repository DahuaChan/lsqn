package cn.cdahua.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cdahua.exception.MyException;
import cn.cdahua.model.Admin;
import cn.cdahua.model.Author;
import cn.cdahua.servers.IAdminServer;
import cn.cdahua.servers.IAuthorServer;
import cn.cdahua.util.ActionUtils;
import cn.cdahua.util.ValidaImageUtil;

@SuppressWarnings("serial")
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport implements ModelDriven<Admin> {
	private IAuthorServer authorServer;
	private IAdminServer adminServer;
	private Admin admin;
	private Author author;

	public IAuthorServer getAuthorServer() {
		return authorServer;
	}

	@Resource
	public void setAuthorServer(IAuthorServer authorServer) {
		this.authorServer = authorServer;
	}

	public IAdminServer getAdminServer() {
		return adminServer;
	}

	@Resource
	public void setAdminServer(IAdminServer adminServer) {
		this.adminServer = adminServer;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public Admin getModel() {
		if (admin == null)
			admin = new Admin();
		return admin;
	}

	public String login() {
		//判断验证码
		String validNum = ServletActionContext.getRequest().getParameter("validNum");
		if(validNum == null) validNum = "";
		if(!validNum.toString().trim().equals(ValidaImageUtil.RANDOM)){
			throw new MyException("验证码错误");
		}
		
		if (admin.getAdmin_account() != null) {
			admin = adminServer.login(admin);
			ActionContext.getContext().getSession().put("admin", admin);
			ActionUtils.setUrl("admin_index.action");
			return ActionUtils.REDIRECT;
		} else {
			if (author == null)
				author = new Author();
			author.setA_email(ServletActionContext.getRequest().getParameter("username"));
			author.setA_password(ServletActionContext.getRequest().getParameter("password"));
			author = authorServer.login(author);
			ActionContext.getContext().getSession().put("author", author);
			ActionUtils.setUrl("author_index.action");
			return ActionUtils.REDIRECT;
		}
	}
	
	public String logout(){
		ActionContext.getContext().getSession().clear();
		return ActionUtils.LOGININPUT;
	}
	
	public String getValidaNumImage() throws IOException{
		ImageIO.write(ValidaImageUtil.getNumValidaImage(), "jpg", ServletActionContext.getResponse().getOutputStream());
		return null;
	}

}
