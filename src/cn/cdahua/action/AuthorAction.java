package cn.cdahua.action;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cdahua.model.Admin;
import cn.cdahua.model.Author;
import cn.cdahua.servers.IAuthorServer;
import cn.cdahua.util.ActionUtils;

@SuppressWarnings("serial")
@Controller("authorAction")
@Scope("prototype")
public class AuthorAction extends ActionSupport implements ModelDriven<Author> {

	private Author author;
	private IAuthorServer authorServer;

	public Author getAuthor() {
		return author;
	}

	@Override
	public Author getModel() {
		if (author == null)
			author = new Author();
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public IAuthorServer getAuthorServer() {
		return authorServer;
	}

	@Resource
	public void setAuthorServer(IAuthorServer authorServer) {
		this.authorServer = authorServer;
	}

	public String index() {
		return SUCCESS;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String add() {
		authorServer.add(author);
		ActionContext.getContext().put("url", "/author_list.action");
		return ActionUtils.REDIRECT;
	}

	public String updateInput() {
		Author a = authorServer.load(author.getA_id());
		BeanUtils.copyProperties(a, author);
		return SUCCESS;
	}

	public String update() {
		Author a = authorServer.load(author.getA_id());
		if(author.getA_password() != a.getA_password()){
			a.setA_password(author.getA_password());
		}else{
			a.setA_name(author.getA_name());			
			a.setA_nickname(author.getA_nickname());
		}
		authorServer.update(a);
		ActionContext.getContext().getSession().replace("author", a);
		ActionContext.getContext().put("url", "/author_list.action");
		return ActionUtils.REDIRECT;
	}

	public String reset() {
		Author a = authorServer.load(author.getA_id());
		a.setA_password(Author.DEFAULTPASSWORD);
		authorServer.update(a);
		ActionContext.getContext().put("url", "/author_list.action");
		return ActionUtils.REDIRECT;
	}

	public String delete() {
		authorServer.delete(author.getA_id());
		ActionContext.getContext().put("url", "author_list.action");
		return ActionUtils.REDIRECT;
	}

	public String list() {
		Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
		if (admin == null) {
			Author author = (Author) ActionContext.getContext().getSession().get("author");
			if (author != null) {
				ActionContext.getContext().put("aup", authorServer.authorFindAuthor(author.getA_id()));
				return SUCCESS;
			} else {
				return ActionUtils.INDEX;
			}
		} else {
			ActionContext.getContext().put("aup", authorServer.findAuthor());
			return SUCCESS;
		}
	}

	public String loginInput() {
		return SUCCESS;
	}

	public String register() {
		if (author.getA_email() == null || author.getA_password() == null || author.getA_email().equals("")
				|| author.getA_password().equals("")) {
			ActionUtils.setUrl("register.jsp");
			return ActionUtils.REDIRECT;
		} else {
			System.out.println(author);
			authorServer.add(author);
			ActionContext.getContext().getSession().put("author", authorServer.loadByEmail(author.getA_email()));
			ActionUtils.setUrl("author_index.action");
			return ActionUtils.REDIRECT;
		}
	}
	
	public String updatePasswordInput(){
		return SUCCESS;
	}

}
