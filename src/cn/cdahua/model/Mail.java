package cn.cdahua.model;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import cn.cdahua.util.MailUtil;

public class Mail {
	public static final String ENCODEING = "UTF-8";

	private static InternetAddress sender; // 发件人的邮箱

	private Address[] receiver; // 收件人的邮箱

	private String subject; // 主题

	private String message; // 信息(支持HTML)

	public Mail() {

	}

	public Mail(String[] re, String subject, String message) {
		try {
			receiver = new Address[re.length];
			for (int i = 0; i < re.length; i++) {
				receiver[i] = new InternetAddress(re[0]);
			}
			this.subject = subject;
			this.message = message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static InternetAddress getSender() {
		if (sender == null) {
			try {
				sender = new InternetAddress(MailUtil.USER);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sender;
	}

	public Address[] getReceiver() {
		return receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
