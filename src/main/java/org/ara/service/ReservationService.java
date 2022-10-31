package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ReservationVO;

public interface ReservationService {
	public void insert(ReservationVO rvo);
	public ArrayList<ReservationVO> select(ReservationVO rvo);
	public int delete(ReservationVO rvo);
	public int update(ReservationVO rvo);
	public void rnoInsert(ReservationVO rvo);
	public void status(ReservationVO rvo);
	public ArrayList<ReservationVO> r_select(ReservationVO rvo);
}
