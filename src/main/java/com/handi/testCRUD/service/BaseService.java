package com.handi.testCRUD.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.handi.testCRUD.model.BaseModel;


@NoRepositoryBean
// Base Interface service layer yang akan di extend oleh actual interface model
public interface BaseService<B extends BaseModel, ID> {

	public List<B> findAll();
	
	public B findById(ID id);
	
	public B save(B entity) throws Exception;
	
	public B update(ID id, B entity) throws Exception;
	
	public int softDelete(ID id) throws Exception;
	
}
