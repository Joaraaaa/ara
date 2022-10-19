package org.ara.model;

public class ReservationVO {
	private String rno;
	private int bno;
	private String date;
	private int tno;
	private int r_time;
	private String email;
	private int r_name;
	private int r_phone;

	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public int getR_time() {
		return r_time;
	}
	public void setR_time(int r_time) {
		this.r_time = r_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getR_name() {
		return r_name;
	}
	public void setR_name(int r_name) {
		this.r_name = r_name;
	}
	public int getR_phone() {
		return r_phone;
	}
	public void setR_phone(int r_phone) {
		this.r_phone = r_phone;
	}
	@Override
	public String toString() {
		return "ReservationVO [rno=" + rno + ", bno=" + bno + ", date=" + date + ", tno=" + tno + ", r_time=" + r_time
				+ ", email=" + email + ", r_name=" + r_name + ", r_phone=" + r_phone + "]";
	}
	
}
