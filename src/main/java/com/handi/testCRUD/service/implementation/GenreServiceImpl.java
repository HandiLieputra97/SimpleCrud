package com.handi.testCRUD.service.implementation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.handi.testCRUD.model.Genre;
import com.handi.testCRUD.repository.GenreRepository;
import com.handi.testCRUD.service.GenreService;
import com.handi.testCRUD.utility.Utility;

@Service
//Implementasi dari service interface Genre
public class GenreServiceImpl implements GenreService{

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired Utility utility;
	
	@Override
	// fungsi untuk mendapatkan semua data Genre
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	// fungsi untuk mendapatkan sebuah data Genre
	public Genre findById(Long id) {
		return genreRepository.findById(id).get();
	}

	@Override
	// fungsi untuk menyimpan data Genre
	public Genre save(Genre entity) throws Exception {
		// mengecek apakah ada data yang belum lengkap
		if(entity.getNama().trim().equals(""))
		{
			throw new Exception("Name Can't be empty");
		}
		entity.setCreatedDate(utility.getDateNow());
		return genreRepository.save(entity);
	}

	@Override
	// fungsi untuk memperbaharui data Genre
	public Genre update(Long id, Genre entity) throws Exception {
		// mengecek apakah ada data yang belum lengkap
		if(entity.getNama().trim().equals(""))
		{
			throw new Exception("Name Can't be empty");
		}
		Genre genre = genreRepository.findById(id).get();
		genre.setNama(entity.getNama());
		genre.setModifiedDate(utility.getDateNow());
		return genreRepository.save(genre);
	}

	@Override
	// Fungsi untuk menghapus data Genre
	public int softDelete(Long id) throws Exception {
		return genreRepository.softDelete(id);
		
	}
}
