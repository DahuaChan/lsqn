package cn.cdahua.dao;

import cn.cdahua.model.Message;

public interface IMessageDao extends IBaseDao<Message> {
	
	public long countByAdmin(int admin_id);
	public long countByAuthor(int a_id);
	
}
