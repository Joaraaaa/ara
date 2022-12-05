package org.ara.model;
// 바꾸고 없애자
public class M_ResSetVO {
	
	private boolean r_status;
	private int bno;
	private String date;
	private int r_time;
	private int people;
	private String rno;
	public boolean isR_status() {
		return r_status;
	}
	public void setR_status(boolean r_status) {
		this.r_status = r_status;
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
	public int getR_time() {
		return r_time;
	}
	public void setR_time(int r_time) {
		this.r_time = r_time;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	@Override
	public String toString() {
		return "M_ResSetVO [r_status=" + r_status + ", bno=" + bno + ", date=" + date + ", r_time=" + r_time + ", people="
				+ people + ", rno=" + rno + "]";
	}
	
}
