package cn.cdahua.servers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cdahua.dao.IAdminDao;
import cn.cdahua.dao.IMessageDao;
import cn.cdahua.exception.MyException;
import cn.cdahua.model.Admin;
import cn.cdahua.model.Mail;
import cn.cdahua.model.Message;
import cn.cdahua.model.Pager;
import cn.cdahua.util.MailUtil;

@Service("adminServer")
public class AdminServer implements IAdminServer {

	private IAdminDao adminDao;
	private IMessageDao messageDao;

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	@Resource
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public IAdminDao getAdminDao() {
		return adminDao;
	}

	@Resource
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void add(Admin admin) {
		adminDao.add(admin);

	}

	@Override
	public void delete(int id) {
		long count = messageDao.countByAdmin(id);
		if (count > 0)
			throw new MyException("该管理员存在审核的文章");
		adminDao.delete(id);
	}

	@Override
	public void update(Admin admin) {
		adminDao.update(admin);
	}

	@Override
	public Admin load(int id) {
		return adminDao.load(id);
	}

	@Override
	public Admin loadByName(String name) {
		String hql = "select admin from Admin admin where admin.admin_account = ?";
		return (Admin) adminDao.queryByHql(hql, name);
	}

	@Override
	public List<Admin> listAllAdmin() {
		return adminDao.loadAll();
	}

	@Override
	public Pager<Admin> findAdmin() {
		String hql = "from Admin";
		return adminDao.find(hql);
	}

	@Override
	public Admin login(Admin admin) {
		Admin a = this.loadByName(admin.getAdmin_account());
		if (a == null)
			throw new MyException("用户不存在");
		if (!a.getAdmin_password().equals(admin.getAdmin_password()))
			throw new MyException("用户账号或密码不正确");
		return a;
	}

	public void sendMail(String receiver, int status) {
		String[] receivers = new String[] { receiver };
		String subject = "岭师青年投稿情况通知";
		String message = "";
		if (status == Message.MSGACCEPT)
			message = "您好！您向岭师青年投送的稿件已被采用，谢谢您对岭师青年的支持，期待您的下次投稿~";
		else if (status == Message.MSGNOTACCEPT)
			message = "您好，您向岭师青年投送的稿件未通过审核，感谢您对岭师青年的支持，继续加油哦，说不定下次就行了，欢迎下次来稿~";
		else
			return;
		Mail mail = new Mail(receivers, subject, message);
		Thread t= new Thread(){
			@Override
			public void run() {
				MailUtil.send(mail);
			}
		};
		t.start();
	}

}
