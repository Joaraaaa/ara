package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.StoreVO;

public interface StoreMapper {

	public void insert(StoreVO svo);
	
	public StoreVO select(StoreVO svo);
	
	public void update(StoreVO svo);

	public ArrayList<StoreVO> selectAll();
}

