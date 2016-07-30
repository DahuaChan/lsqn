package cn.cdahua.servers;

import java.util.List;

import cn.cdahua.model.Message;
import cn.cdahua.model.Pager;

public interface IMessageServer {

	public void add(Message msg, int a_id);

	public void delete(int id);

	public void update(Message msg, int admin_id);

	public Message load(int id);

	public List<Message> listAllMessage();

	public List<Message> listByAuthor(int a_id);

	public Pager<Message> findMessage();

	public Pager<Message> authorFindMessage(int a_id);

	public Pager<Message> findMessageByIsRead();

	public Pager<Message> authorFindMessageByIsRead(int a_id);


	public Pager<Message> findMessageByUnRead();

	public Pager<Message> authorFindMessageByUnRead(int a_id);

}
