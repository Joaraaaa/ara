package org.ara.service;

import java.util.ArrayList;

import org.ara.model.StoreVO;

public interface StoreService {

	public void insert(StoreVO svo);
	public StoreVO select(StoreVO svo);
	public void update(StoreVO svo);
	public ArrayList<StoreVO> selectAll();
	public int bno_s();
}
