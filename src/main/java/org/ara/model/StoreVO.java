package org.ara.model;
// 가게 정보 VO
public class StoreVO {
	private int s_no;
	private String s_name;
	private String address;
	private int o_time;
	private int c_time;
	private int f_time;
	private int l_time;
	private int cycle;
	private int p_set;
	private int p_min;
	private int p_max;
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getO_time() {
		return o_time;
	}
	public void setO_time(int o_time) {
		this.o_time = o_time;
	}
	public int getC_time() {
		return c_time;
	}
	public void setC_time(int c_time) {
		this.c_time = c_time;
	}
	public int getF_time() {
		return f_time;
	}
	public void setF_time(int f_time) {
		this.f_time = f_time;
	}
	public int getL_time() {
		return l_time;
	}
	public void setL_time(int l_time) {
		this.l_time = l_time;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public int getP_set() {
		return p_set;
	}
	public void setP_set(int p_set) {
		this.p_set = p_set;
	}
	public int getP_min() {
		return p_min;
	}
	public void setP_min(int p_min) {
		this.p_min = p_min;
	}
	public int getP_max() {
		return p_max;
	}
	public void setP_max(int p_max) {
		this.p_max = p_max;
	}
	@Override
	public String toString() {
		return "StoreVO [s_no=" + s_no + ", s_name=" + s_name + ", address=" + address + ", o_time=" + o_time
				+ ", c_time=" + c_time + ", f_time=" + f_time + ", l_time=" + l_time + ", cycle=" + cycle + ", p_set="
				+ p_set + ", p_min=" + p_min + ", p_max=" + p_max + "]";
	}
	
	
}
