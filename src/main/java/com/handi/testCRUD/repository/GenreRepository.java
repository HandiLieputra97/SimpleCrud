package com.handi.testCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handi.testCRUD.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
//repository untuk CRUD Genre
public interface GenreRepository extends BaseRepository<Genre, Long>{
	
}
