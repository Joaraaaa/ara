package org.ara.model;

public class MemberVO {
	private boolean sns;
	private String email;
	private String password;
	private String n_name;
	private String gender;
	private String birth_y;
	private java.sql.Timestamp sign_date;
	public boolean isSns() {
		return sns;
	}
	public void setSns(boolean sns) {
		this.sns = sns;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getN_name() {
		return n_name;
	}
	public void setN_name(String n_name) {
		this.n_name = n_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth_y() {
		return birth_y;
	}
	public void setBirth_y(String birth_y) {
		this.birth_y = birth_y;
	}
	public java.sql.Timestamp getSign_date() {
		return sign_date;
	}
	public void setSign_date(java.sql.Timestamp sign_date) {
		this.sign_date = sign_date;
	}
	@Override
	public String toString() {
		return "MemberVO [sns=" + sns + ", email=" + email + ", password=" + password + ", n_name=" + n_name
				+ ", gender=" + gender + ", birth_y=" + birth_y + ", sign_date=" + sign_date + "]";
	}
}
