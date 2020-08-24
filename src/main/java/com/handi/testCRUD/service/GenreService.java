package com.handi.testCRUD.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.handi.testCRUD.model.Genre;

@Service
// Service Interface untuk model Genre
public interface GenreService extends BaseService<Genre, Long>{


}
