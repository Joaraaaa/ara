package org.ara.service;

import org.ara.model.ReservationVO;

public interface ReservationService {
	public void insert(ReservationVO rvo);
	public ReservationVO select(String bno);
}
