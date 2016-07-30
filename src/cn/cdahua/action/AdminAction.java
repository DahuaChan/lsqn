package cn.cdahua.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cdahua.model.Admin;
import cn.cdahua.model.SystemContext;
import cn.cdahua.servers.IAdminServer;
import cn.cdahua.util.ActionUtils;

@SuppressWarnings("serial")
@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	private Admin admin;
	private IAdminServer adminServer;
	private static final Map<Integer,String> ADMINTYPE = getAdminType();
	
	private static Map<Integer, String> getAdminType(){
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(Admin.UNSUADMIN, "普通管理员");
		map.put(Admin.ISSUADMIN, "超级管理员");
		SystemContext.setAdminType(map);
		return SystemContext.getAdminType();
	}
	

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public IAdminServer getAdminServer() {
		return adminServer;
	}

	@Resource
	public void setAdminServer(IAdminServer adminServer) {
		this.adminServer = adminServer;
	}
	
	public String index(){
		return SUCCESS;
	}
	
	private void setAdminTypeToView(){
		ActionContext.getContext().put("adminType", ADMINTYPE);
	}
	
	public String addInput() {
		setAdminTypeToView();
		return SUCCESS;
	}
	
	public String add() {
		System.out.println(admin);
		adminServer.add(admin);
		ActionContext.getContext().put("url", "/admin_list.action");
		return ActionUtils.REDIRECT;
	}

	public String updateInput() {
		setAdminTypeToView();
		Admin ad = adminServer.load(admin.getAdmin_id());
		BeanUtils.copyProperties(ad, admin);
		return SUCCESS;
	}

	public String update() {
		Admin a = adminServer.load(admin.getAdmin_id());
		a.setAdmin_id(admin.getAdmin_id());
		a.setAdmin_account(admin.getAdmin_account());
		a.setAdmin_type(admin.getAdmin_type());
		adminServer.update(a);
		ActionContext.getContext().put("url", "/admin_list.action");
		return ActionUtils.REDIRECT;
	}

	public String delete() {
		System.out.println(admin.toString());
		adminServer.delete(admin.getAdmin_id());
		ActionContext.getContext().put("url", "admin_list.action");
		return ActionUtils.REDIRECT;
	}

	public String list() {
		setAdminTypeToView();
		ActionContext.getContext().put("adp", adminServer.findAdmin());
		return SUCCESS;
	}
	
	public String loginInput(){
		return SUCCESS;
	}

	@Override
	public Admin getModel() {
		if (admin == null)
			admin = new Admin();
		return admin;
	}

}
