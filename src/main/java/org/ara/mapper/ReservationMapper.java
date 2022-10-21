package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.ReservationVO;

public interface ReservationMapper {
	public void insert(ReservationVO rvo);
	public ArrayList<ReservationVO> select(int bno);
	public int delete(ReservationVO rvo);
	public int update(ReservationVO rvo);
}
