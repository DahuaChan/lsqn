package cn.cdahua.dao;

import org.springframework.stereotype.Repository;

import cn.cdahua.model.Message;

@Repository("messageDao")
public class MessageDao extends BaseDao<Message> implements IMessageDao{

	@Override
	public long countByAdmin(int admin_id) {
		String hql = "select count(*) from Message where admin.admin_id=?";
		long count = (Long)this.getSession().createQuery(hql)
					.setParameter(0, admin_id).uniqueResult();
		return count;
	}

	@Override
	public long countByAuthor(int a_id) {
		String hql = "select count(*) from Message where author.a_id=?";
		long count = (Long)this.getSession().createQuery(hql)
					.setParameter(0, a_id).uniqueResult();
		return count;
	}
	
	

}
