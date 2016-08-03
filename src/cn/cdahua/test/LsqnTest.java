package cn.cdahua.test;

import java.util.ArrayList;
import java.util.List;

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
import cn.cdahua.util.StringUtil;
import cn.cdahua.util.Word2htmlUtil;

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
		String a = "{\"path0\":\"附件：团学系统评优申报表.docx?http://localhost:8080/lsqn/upload/file/20160802/1470135175983041442.docx\",\"path1\":\"审核项目2015-2016（校团委版）.doc?http://localhost:8080/lsqn/upload/file/20160802/1470135175983020268.doc\",\"path2\":\"审核项目2015-2016（学生会版）.docx?http://localhost:8080/lsqn/upload/file/20160802/1470141465808044898.docx\"}";
		String b = "D:/test/123.doc";
		List<String> lists = new ArrayList<>();
		lists.add(b);
		Word2htmlUtil.convert2Html(lists);
		System.out.println("test:"+b);
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
