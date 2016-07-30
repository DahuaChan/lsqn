package cn.cdahua.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cdahua.model.Author;
import cn.cdahua.model.Mail;
import cn.cdahua.model.Message;
import cn.cdahua.servers.AuthorServer;
import cn.cdahua.servers.IMessageServer;
import cn.cdahua.servers.MessageServer;
import cn.cdahua.util.MailUtil;

public class LsqnTest {

	@Test
	public void test() {
		String[] receiver = new String[]{"313921056@qq.com"};
		String subject = "我的第一封邮件";
		String message = "猜猜我是谁？";
		Mail mail = new Mail(receiver,subject,message);
		if(MailUtil.send(mail)){
			System.out.println("chenggong");
		}else{
			System.out.println("shibai");
			
		}
		
	}

	@Test
	public void test2() {
		BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
		AuthorServer as = (AuthorServer) factory.getBean("authorServer");
		Author a = new Author();
		a.setA_email("313921056@qq.com");
		a.setA_nickname("华仔");
		a.setA_name("达华");
		a.setA_password("1234");
		a.setA_id(2);
		as.delete(2);
		System.out.println(a.toString());
	}

	@Test
	public void test3() {
		BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
		IMessageServer messageServer = (MessageServer) factory.getBean("messageServer");
		Message msg = new Message();
		msg.setMsg_id(2);
		msg.setMsg_content("哇哈哈哈哈");
		msg.setMsg_title("第一篇文章");
		msg.setMsg_status(0);
		messageServer.update(msg, 1);
	}
}
