package cn.cdahua.servers;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cdahua.dao.IAdminDao;
import cn.cdahua.dao.IAuthorDao;
import cn.cdahua.dao.IMessageDao;
import cn.cdahua.model.Message;
import cn.cdahua.model.Pager;

@Service("messageServer")
public class MessageServer implements IMessageServer {
	
	private IAdminDao adminDao;
	private IAuthorDao authorDao;
	private IMessageDao messageDao;
	
	

	public IAdminDao getAdminDao() {
		return adminDao;
	}

	@Resource
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public IAuthorDao getAuthorDao() {
		return authorDao;
	}
	
	@Resource
	public void setAuthorDao(IAuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public IMessageDao getMessageDao() {
		return messageDao;
	}
	
	@Resource
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public void add(Message msg, int a_id) {
		msg.setAuthor(authorDao.load(a_id));
		msg.setMsg_date(new Date());
		messageDao.add(msg);
	}

	@Override
	public void delete(int id) {
		messageDao.delete(id);
		
	}

	@Override
	public void update(Message msg, int admin_id) {
		if(admin_id!=0)
			msg.setAdmin(adminDao.load(admin_id));
		messageDao.update(msg);
	}

	@Override
	public Message load(int id) {
		return messageDao.load(id);
	}

	@Override
	public List<Message> listAllMessage() {
		return messageDao.loadAll();
	}

	@Override
	public List<Message> listByAuthor(int a_id) {
		String hql = "from message where author.id=? order by msg.msg_date desc";
		return messageDao.list(hql, a_id);
	}

	@Override
	public Pager<Message> findMessage() {
		String hql = "from Message msg left join fetch msg.admin left join fetch msg.author order by msg.msg_date desc";
		return messageDao.find(hql);
	}

	@Override
	public Pager<Message> findMessageByIsRead() {
		String hql = "from Message msg left join fetch msg.admin left join fetch msg.author where msg.msg_status = "+Message.MSGACCEPT+" order by msg.msg_date desc";
		return messageDao.find(hql);
	}

	@Override
	public Pager<Message> findMessageByUnRead() {
		String hql = "from Message msg left join fetch msg.admin left join fetch msg.author where msg.admin = null order by msg.msg_date desc";
		return messageDao.find(hql);
	}

	@Override
	public Pager<Message> authorFindMessage(int a_id) {
		String hql = "from Message msg left join fetch msg.admin left join fetch msg.author where msg.author = "+a_id+" order by msg.msg_date desc";
		return messageDao.find(hql);
	}

	@Override
	public Pager<Message> authorFindMessageByIsRead(int a_id) {
		String hql = "from Message msg left join fetch msg.admin left join fetch msg.author where msg.msg_status = "+Message.MSGACCEPT+" and msg.author = "+a_id+" order by msg.msg_date desc";
		return messageDao.find(hql);
	}

	@Override
	public Pager<Message> authorFindMessageByUnRead(int a_id) {
		String hql = "from Message msg left join fetch msg.admin left join fetch msg.author where msg.admin = null and msg.author = "+a_id+" order by msg.msg_date desc";
		return messageDao.find(hql);
	}

}
