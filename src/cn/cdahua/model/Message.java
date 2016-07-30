package cn.cdahua.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * msg_id int auto_increment primary key, msg_title varchar(255), msg_content
 * text, msg_status int, a_id int, admin_id int,
 * 
 * @author dahua
 *
 */
@Entity
@Table(name = "message")
public class Message {
	
	
	public static final int MSGACCEPT = 2;
	public static final int MSGUNCHECK = 1;
	public static final int MSGNOTACCEPT = 0;

	private int msg_id;
	private String msg_title;
	private String msg_content;
	private int msg_status = MSGUNCHECK;
	private Date msg_date;
	private Author author;
	private Admin admin;
	
	
	public Date getMsg_date() {
		return msg_date;
	}

	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}

	@ManyToOne
	@JoinColumn(name="a_id")
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	@ManyToOne
	@JoinColumn(name="admin_id")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Id
	@GeneratedValue
	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public int getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(int msg_status) {
		this.msg_status = msg_status;
	}

	@Override
	public String toString() {
		return "Message [msg_id=" + msg_id + ", msg_title=" + msg_title + ", msg_content=" + msg_content
				+ ", msg_status=" + msg_status + ", author=" + author + ", admin=" + admin + "]";
	}
	
	

}
