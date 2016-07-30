package cn.cdahua.servers;

import java.util.List;

import cn.cdahua.model.Admin;
import cn.cdahua.model.Pager;

public interface IAdminServer {

	public void add(Admin admin);

	public void delete(int id);

	public void update(Admin admin);

	public Admin load(int id);

	public Admin loadByName(String name);

	public List<Admin> listAllAdmin();

	public Pager<Admin> findAdmin();

	public Admin login(Admin admin);
	
	public void sendMail(String receiver,int msg_status);
}
