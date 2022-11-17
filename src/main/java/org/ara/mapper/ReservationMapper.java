package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.RUserInfoVO;
import org.ara.model.ResSetVO;
import org.ara.model.ReservationVO;

public interface ReservationMapper {
	public void insert(ResSetVO rsvo);
	public void update(ResSetVO rsvo);
	public ArrayList<ResSetVO> select(ResSetVO rsvo);
	public int delete(RUserInfoVO ruivo);
//	public int update(ReservationVO rvo);
	public void rnoInsert(ReservationVO rvo);
	public void status(ReservationVO rvo);
	public ArrayList<ReservationVO> r_select(ReservationVO rvo);
	public ArrayList<RUserInfoVO> list(RUserInfoVO ruivo);
	public int rpselect(RUserInfoVO ruivo);
	public int pselect(ResSetVO rsvo);
	public void addres(RUserInfoVO ruivo);
	public void upres(RUserInfoVO ruivo);
}
