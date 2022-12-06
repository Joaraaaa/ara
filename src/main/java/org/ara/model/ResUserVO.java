package org.ara.model;
// 이름 바꾸자 예약자 정보 VO
public class ResUserVO {
	private int s_no;
	private String dt_no;
	private int r_no;
	private String email;
	private String r_name;
	private String r_phone;
	private int r_people;
	private String r_memo;
	private boolean v_status;
	private ResSetVO rsvo;
	private StoreVO svo;
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getDt_no() {
		return dt_no;
	}
	public void setDt_no(String dt_no) {
		this.dt_no = dt_no;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
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
	public ResSetVO getRsvo() {
		return rsvo;
	}
	public void setRsvo(ResSetVO rsvo) {
		this.rsvo = rsvo;
	}
	public StoreVO getSvo() {
		return svo;
	}
	public void setSvo(StoreVO svo) {
		this.svo = svo;
	}
	@Override
	public String toString() {
		return "ResUserVO [s_no=" + s_no + ", dt_no=" + dt_no + ", r_no=" + r_no + ", email=" + email + ", r_name="
				+ r_name + ", r_phone=" + r_phone + ", r_people=" + r_people + ", r_memo=" + r_memo + ", v_status="
				+ v_status + ", rsvo=" + rsvo + ", svo=" + svo + "]";
	}
	
}
