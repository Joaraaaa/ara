package org.ara.service;

import java.util.ArrayList;

import org.ara.model.ReservationVO;

public interface ReservationService {
	public void insert(ReservationVO rvo);
	public ArrayList<ReservationVO> select(int bno);
	public void delete(ReservationVO rvo);
}
