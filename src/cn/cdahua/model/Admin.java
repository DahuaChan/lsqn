package cn.cdahua.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	public static final int UNSUADMIN = 0;
	public static final int ISSUADMIN = 1;
	
	private int admin_id;
	private String admin_account;
	private String admin_password;
	private int admin_type;

	@Id
	@GeneratedValue
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public int getAdmin_type() {
		return admin_type;
	}

	public void setAdmin_type(int admin_type) {
		this.admin_type = admin_type;
	}

	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_account=" + admin_account + ", admin_password=" + admin_password
				+ ", admin_type=" + admin_type + "]";
	}
	
	
}
