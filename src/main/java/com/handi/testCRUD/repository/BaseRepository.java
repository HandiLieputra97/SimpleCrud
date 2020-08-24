package com.handi.testCRUD.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.handi.testCRUD.model.BaseModel;

@NoRepositoryBean
//Base interface repository yang akan di extend oleh actual repository class 
public interface BaseRepository<T extends BaseModel,ID> extends JpaRepository<T, ID> {

	// Fungsi & Query untuk find all dengan deleted = false 
	@Query("select e from #{#entityName} e where e.deleted=false")
    public List<T> findAll();
	
	// Fungsi & Query untuk find By id dengan deleted = false 
	@Query("select e from #{#entityName} e where e.deleted=false and e.id=?1")
    public Optional<T> findById(ID id);

	// Fungsi & Query untuk find all deleted dengan deleted = true 
	@Query("select e from #{#entityName} e where e.deleted=true")
    public List<T> findAllDeleted();

	// Fungsi & Query untuk soft delete 
	@Transactional
	@Modifying
	@Query("update #{#entityName} e set e.deleted=true where e.id=?1 and e.deleted=false")
    public int softDelete(ID id);
	
}
