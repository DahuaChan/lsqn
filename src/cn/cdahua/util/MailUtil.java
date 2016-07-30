package cn.cdahua.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import cn.cdahua.model.Mail;


public class MailUtil {
	protected final Logger logger = Logger.getLogger(getClass());
	
	public static final String USER = "lnsytw2015@sina.com"; 
	private static final String PASSWORD = "newlnsytw2016";
	private static final Properties PROPS = PropertiesUtil.getMailProp();
	private static final Session SESSION = Session.getInstance(PROPS);
	
    public static boolean send(Mail mail) {  
        try {
        	Message msg = new MimeMessage(SESSION);
        	msg.setSubject(mail.getSubject());
        	msg.setText(mail.getMessage());
        	msg.setFrom(Mail.getSender());
			Transport transport = SESSION.getTransport();
			transport.connect(USER, PASSWORD);
			transport.sendMessage(msg, mail.getReceiver());
			transport.close();  
            return true;
			
		} catch (NoSuchProviderException e) { 
			System.out.println(e.getMessage());
			return false;
		} catch (MessagingException e) { 
			System.out.println(e.getMessage());
			return false;
		}  
    }  
}
