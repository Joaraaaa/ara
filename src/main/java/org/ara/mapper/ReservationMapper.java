package org.ara.mapper;

import org.ara.model.ReservationVO;

public interface ReservationMapper {
	public void insert(ReservationVO rvo);
	public ReservationVO select(String bno);
}
