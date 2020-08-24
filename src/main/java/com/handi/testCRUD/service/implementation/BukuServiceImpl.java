package com.handi.testCRUD.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.handi.testCRUD.model.Buku;
import com.handi.testCRUD.repository.BukuRepository;
import com.handi.testCRUD.service.BukuService;
import com.handi.testCRUD.utility.Utility;

@Service
// Implementasi dari service interface Buku
public class BukuServiceImpl implements BukuService{
	
	@Autowired
	private BukuRepository bukuRepository;

	@Autowired Utility utility;
	
	@Override
	// fungsi untuk mendapatkan semua data Buku
	public List<Buku> findAll() {
		return bukuRepository.findAll();
	}

	@Override
	// fungsi untuk mendapatkan sebuah data Buku
	public Buku findById(Long id) {
		return bukuRepository.findById(id).get();
	}

	@Override
	// fungsi untuk Menyimpan data Buku
	public Buku save(Buku entity) throws Exception {
		// mengecek apakah ada data yang belum lengkap
		if(entity.getJudul().trim().equals("") || entity.getIsbn().trim().equals("") || entity.getPenerbit().trim().equals("")
			|| entity.getPenulis().trim().equals("") || entity.getTahunTerbit().trim().equals(""))
		{
			throw new Exception("Please Fill Required Field");
		}
		entity.setCreatedDate(utility.getDateNow());
		return bukuRepository.save(entity);
	}

	@Override
	// fungsi untuk Memperbaharui data Buku
	public Buku update(Long id, Buku entity) throws Exception {
		// mengecek apakah ada data yang belum lengkap
		if(entity.getJudul().trim().equals("") || entity.getIsbn().trim().equals("") || entity.getPenerbit().trim().equals("")
				|| entity.getPenulis().trim().equals("") || entity.getTahunTerbit().trim().equals(""))
		{
			throw new Exception("Please Fill Required Field");
		}
		Buku buku = bukuRepository.findById(id).get();
		buku.setGenres(entity.getGenres());
		buku.setIsbn(entity.getIsbn());
		buku.setJudul(entity.getJudul());
		buku.setPenerbit(entity.getPenerbit());
		buku.setPenulis(entity.getPenulis());
		buku.setSinopsis(entity.getSinopsis());
		buku.setTahunTerbit(entity.getTahunTerbit());
		buku.setModifiedDate(utility.getDateNow());
		
		return bukuRepository.save(buku);
		
	}

	@Override
	// Fungsi untuk menghapus data Buku
	public int softDelete(Long id) throws Exception {
		return bukuRepository.softDelete(id);
	}

}
