package com.handi.testCRUD;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.handi.testCRUD.model.Buku;
import com.handi.testCRUD.model.Genre;
import com.handi.testCRUD.repository.BukuRepository;
import com.handi.testCRUD.service.implementation.BukuServiceImpl;
import com.handi.testCRUD.utility.Utility;

//extend mockito extension agar tidak usah me declare mockito annotation
@ExtendWith(MockitoExtension.class)
public class BukuServiceTest {
	// membuat mock object untuk komponen yang dibutuhkan dari repository
	@Mock
	private BukuRepository bukuRepository;
	
	// membuat mock object untuk komponen yang dibutuhkan dari utility
	@Mock
	private Utility utility;
	
	// membuat object dependency untuk mensimulasi test dari service layer Buku
	@InjectMocks 
	private BukuServiceImpl bukuServiceImpl;
	
	// Test 1
	// Test success untuk save Buku
	@Test
	void successForSaveBuku() throws Exception {
		// mengkondisikan agar save Buku Berhasil
		Buku buku = new Buku();
		buku.setIsbn("Test ISBN");
		buku.setJudul("Test Judul");
		buku.setPenerbit("Test Penerbit");
		buku.setPenulis("Test Penulis");
		buku.setSinopsis("Test Sinopsis");
		buku.setTahunTerbit("2020");
		given(bukuRepository.save(buku)).willAnswer(Invocation -> Invocation.getArgument(0));
		Buku savedBuku = bukuServiceImpl.save(buku);
		// membandingkan apakah hasil dari save Buku apabila tidak null maka save berhasil
		assertThat(savedBuku).isNotNull();
		// memverifikasi apakah ada transaksi save dengan tipe object Buku
		verify(bukuRepository).save(any(Buku.class));
	}
	
	// Test 2
	// Test Fail untuk save Buku
	@Test
	void failToSaveBuku() throws Exception{
		// Mengkondisikan agar save Buku Gagal
		Buku buku = new Buku();
		// membandingkan apakah hasil dari save Buku menghasilkan exception atau tidak
		assertThrows(Exception.class, ()->{bukuServiceImpl.save(buku);});
		// memverifikasi apakah ada transaksi save dengan tipe object Buku
		verify(bukuRepository,never()).save(any(Buku.class));
	}
	
	// Test 3
	// Test Update Buku
	@Test
	void updateBuku() throws Exception{
		// Mengkondisikan untuk update Buku
		Buku buku = new Buku();
		buku.setIsbn("Test ISBN");
		buku.setJudul("Test Judul");
		buku.setPenerbit("Test Penerbit");
		buku.setPenulis("Test Penulis");
		buku.setSinopsis("Test Sinopsis");
		buku.setTahunTerbit("2020");
		given(bukuRepository.save(buku)).willReturn(buku);
		Buku updateBuku = bukuServiceImpl.update(1L, buku);
		// Membandingkan apakah hasil dari update Buku menghasilkan null atau tidak
		assertThat(buku).isNotNull();
		// memverifikasi apakah ada transaksi save dengan tipe object genre
		verify(bukuRepository).save(any(Buku.class));
	}
	
	// Test 4
	// Test Update Buku
	@Test
	void failToUpdateBuku() throws Exception{
		// Mengkondisikan untuk update Buku
		Buku buku = new Buku();
		assertThrows(Exception.class, ()->{bukuServiceImpl.update(1L,buku);});
		// memverifikasi apakah ada transaksi update dengan tipe object Buku
		verify(bukuRepository,never()).save(any(Buku.class));
	}
	
	// Test 5
	// Test untuk get All Buku
	@Test
	void getAllBuku() {
		// Mengkonndisikan data get All Buku
		List<Buku> bukus = new ArrayList<Buku>();
		bukus.add(new Buku("Test ISBN1", "Test judul1", "Test Penulis1", "Test Penerbit1", "2020", "Test Sinopsis1", new ArrayList<>()));
		bukus.add(new Buku("Test ISBN2", "Test judul2", "Test Penulis2", "Test Penerbit2", "2021", "Test Sinopsis2", new ArrayList<>()));
		bukus.add(new Buku("Test ISBN3", "Test judul3", "Test Penulis3", "Test Penerbit3", "2022", "Test Sinopsis3", new ArrayList<>()));
		given(bukuRepository.findAll()).willReturn(bukus);
		List<Buku> listBukus = bukuRepository.findAll();
		// membandingkan apakah sama menghasilkan data list dari object buku atau tidak
		assertEquals(listBukus, bukus);
	}
	
	// Test 6
	// Test Delete Buku
	@Test
	void deleteBuku() throws Exception {
		// Mengkondisikan untuk Delete Buku
		final Long bukuId = 1L;
		bukuServiceImpl.softDelete(bukuId);
		// Memverifikasi apakah perintah delete telah di lakukan atau tidak
		verify(bukuRepository,times(1)).softDelete(bukuId);
	}
}
