package org.ara.model;

public class MemberVO {
	private boolean admin;
	private boolean sns;
	private String password;
	private String name;
	private String email;
	private String gender;
	private String birth_y;
	private String birth_m;
	private String birth_d;
	private String sign_date;
	private String userImg;
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isSns() {
		return sns;
	}
	public void setSns(boolean sns) {
		this.sns = sns;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getBirth_m() {
		return birth_m;
	}
	public void setBirth_m(String birth_m) {
		this.birth_m = birth_m;
	}
	public String getBirth_d() {
		return birth_d;
	}
	public void setBirth_d(String birth_d) {
		this.birth_d = birth_d;
	}
	public String getSign_date() {
		return sign_date;
	}
	public void setSign_date(String sign_date) {
		this.sign_date = sign_date;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	@Override
	public String toString() {
		return "MemberVO [admin=" + admin + ", sns=" + sns + ", password=" + password + ", name=" + name + ", email="
				+ email + ", gender=" + gender + ", birth_y=" + birth_y + ", birth_m=" + birth_m + ", birth_d="
				+ birth_d + ", sign_date=" + sign_date + ", userImg=" + userImg + "]";
	}
	
	
}
