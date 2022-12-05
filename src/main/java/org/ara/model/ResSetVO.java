package org.ara.model;
// 예약 스케줄 표 VO
public class ResSetVO {
	private int s_no;
	private boolean r_status;
	private String r_date;
	private int r_time;
	private int people;
	private String dt_no;
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public boolean isR_status() {
		return r_status;
	}
	public void setR_status(boolean r_status) {
		this.r_status = r_status;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
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
	public String getDt_no() {
		return dt_no;
	}
	public void setDt_no(String dt_no) {
		this.dt_no = dt_no;
	}
	@Override
	public String toString() {
		return "ResSetVO [s_no=" + s_no + ", r_status=" + r_status + ", r_date=" + r_date + ", r_time=" + r_time
				+ ", people=" + people + ", dt_no=" + dt_no + "]";
	}
	
}
