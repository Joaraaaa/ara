package org.ara.service;

import org.ara.model.StoreVO;

public interface StoreService {

	public void insert(StoreVO store);
	public StoreVO select(StoreVO store);
	public void update(StoreVO store);
}
