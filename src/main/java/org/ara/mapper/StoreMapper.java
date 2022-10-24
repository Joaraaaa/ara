package org.ara.mapper;

import java.util.ArrayList;

import org.ara.model.StoreVO;

public interface StoreMapper {

	public void insert(StoreVO store);
	
	public StoreVO select(StoreVO store);
	
	public void update(StoreVO store);

	public ArrayList<StoreVO> selectAll();
}

