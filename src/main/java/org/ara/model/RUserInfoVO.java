package org.ara.model;

public class RUserInfoVO {
	private String rno;
	private String email;
	private String r_name;
	private String r_phone;
	private int r_people;
	private String r_memo;
	private boolean v_status;
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_phone() {
		return r_phone;
	}
	public void setR_phone(String r_phone) {
		this.r_phone = r_phone;
	}
	public int getR_people() {
		return r_people;
	}
	public void setR_people(int r_people) {
		this.r_people = r_people;
	}
	public String getR_memo() {
		return r_memo;
	}
	public void setR_memo(String r_memo) {
		this.r_memo = r_memo;
	}
	public boolean isV_status() {
		return v_status;
	}
	public void setV_status(boolean v_status) {
		this.v_status = v_status;
	}
	@Override
	public String toString() {
		return "RUserInfoVO [rno=" + rno + ", email=" + email + ", r_name=" + r_name + ", r_phone=" + r_phone
				+ ", r_people=" + r_people + ", r_memo=" + r_memo + ", v_status=" + v_status + "]";
	}
	
}
