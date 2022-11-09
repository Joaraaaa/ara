package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;

public interface ReservationMapper {
	public void insert(ResSetVO rsvo);
//	public void insert(ReservationVO rvo);
	public ArrayList<ReservationVO> select(ReservationVO rvo);
	public int delete(ReservationVO rvo);
	public int update(ReservationVO rvo);
	public void rnoInsert(ReservationVO rvo);
	public void status(ReservationVO rvo);
	public ArrayList<ReservationVO> r_select(ReservationVO rvo);
}
