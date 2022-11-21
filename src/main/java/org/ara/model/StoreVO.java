package org.ara.model;

public class StoreVO {
	private int bno;
	private String store;
	private String address;
	private int open_time;
	private int close_time;
	private int first;
	private int last;
	private int cycle;
	private int p_setting;
	private int p_min;
	private int p_max;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getOpen_time() {
		return open_time;
	}
	public void setOpen_time(int open_time) {
		this.open_time = open_time;
	}
	public int getClose_time() {
		return close_time;
	}
	public void setClose_time(int close_time) {
		this.close_time = close_time;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public int getP_setting() {
		return p_setting;
	}
	public void setP_setting(int p_setting) {
		this.p_setting = p_setting;
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
		return "StoreVO [bno=" + bno + ", store=" + store + ", address=" + address + ", open_time=" + open_time
				+ ", close_time=" + close_time + ", first=" + first + ", last=" + last + ", cycle=" + cycle
				+ ", p_setting=" + p_setting + ", p_min=" + p_min + ", p_max=" + p_max + "]";
	}
	
	
}
