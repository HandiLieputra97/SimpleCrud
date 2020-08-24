package com.handi.testCRUD.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.handi.testCRUD.model.Buku;

@Service
//Service Interface untuk model Buku
public interface BukuService extends BaseService<Buku, Long>{
	
}
