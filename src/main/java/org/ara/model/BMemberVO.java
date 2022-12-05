package org.ara.model;
// 사업자 회원 정보 VO
public class BMemberVO {
	private int s_no;
	private String c_no;
	private String c_name;
	private String name;
	private String email;
	private String password;
	private String phone;
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "M_BMemberVO [s_no=" + s_no + ", c_no=" + c_no + ", c_name=" + c_name + ", name=" + name + ", email="
				+ email + ", password=" + password + ", phone=" + phone + "]";
	}
}
