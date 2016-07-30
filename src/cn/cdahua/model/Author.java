package cn.cdahua.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * a_id int auto_increment primary key, a_email varchar(255), a_name
 * varchar(255), a_nickname varchar(255), a_password varchar(255)
 * 
 * @author dahua
 *
 */
@Entity
@Table(name="author")
public class Author {
	
	public static final String DEFAULTPASSWORD = "000000";
	
	private int a_id;
	private String a_email;
	private String a_name;
	private String a_nickname;
	private String a_password;
	
	@Id
	@GeneratedValue
	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getA_email() {
		return a_email;
	}

	public void setA_email(String a_email) {
		this.a_email = a_email;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getA_nickname() {
		return a_nickname;
	}

	public void setA_nickname(String a_nickname) {
		this.a_nickname = a_nickname;
	}

	public String getA_password() {
		return a_password;
	}

	public void setA_password(String a_password) {
		this.a_password = a_password;
	}

	@Override
	public String toString() {
		return "Author [a_id=" + a_id + ", a_email=" + a_email + ", a_name=" + a_name + ", a_nickname=" + a_nickname
				+ ", a_password=" + a_password + "]";
	}
	
	

}
