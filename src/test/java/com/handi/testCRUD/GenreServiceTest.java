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

import com.handi.testCRUD.model.Genre;
import com.handi.testCRUD.repository.GenreRepository;
import com.handi.testCRUD.service.implementation.GenreServiceImpl;
import com.handi.testCRUD.utility.Utility;

// extend mockito extension agar tidak usah me declare mockito annotation
@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {
	// membuat mock object untuk komponen yang dibutuhkan dari repository
	@Mock
	private GenreRepository genreRepository;
	// membuat mock object untuk komponen yang dibutuhkan dari utility
	@Mock
	private Utility utility;
	
	// membuat object dependency untuk mensimulasi test dari service layer genre
	@InjectMocks 
	private GenreServiceImpl genreService;
	
	// Test 1
	// Test success untuk save Genre
	@Test
	void successForSaveGenre() throws Exception {
		// mengkondisikan agar save Genre Berhasil
		Genre genre = new Genre();
		genre.setNama("Horror");
		given(genreRepository.save(genre)).willAnswer(Invocation -> Invocation.getArgument(0));
		Genre savedGenre = genreService.save(genre);
		// membandingkan apakah hasil dari save genre apabila tidak null maka save berhasil
		assertThat(savedGenre).isNotNull();
		// memverifikasi apakah ada transaksi save dengan tipe object genre
		verify(genreRepository).save(any(Genre.class));
	}
	
	// Test 2
	// Test Fail untuk save Genre
	@Test
	void failToSaveGenre() throws Exception{
		// Mengkondisikan agar save Genre Gagal
		Genre genre = new Genre();
		genre.setNama("");
		// membandingkan apakah hasil dari save genre menghasilkan exception atau tidak
		assertThrows(Exception.class, ()->{genreService.save(genre);});
		// memverifikasi apakah ada transaksi save dengan tipe object genre
		verify(genreRepository,never()).save(any(Genre.class));
	}
	// Test 3
	// Test Update Genre
	@Test
	void updateGenre() throws Exception{
		// Mengkondisikan untuk update Genre
		Genre genre = new Genre();
		genre.setId(1L);
		genre.setNama("Horror");
		genre.setDeleted(false);
		given(genreRepository.save(genre)).willReturn(genre);
		Genre updateGenre = genreService.update(1L, genre);
		// Membandingkan apakah hasil dari update genre menghasilkan null atau tidak
		assertThat(updateGenre).isNotNull();
		// memverifikasi apakah ada transaksi save dengan tipe object genre
		verify(genreRepository).save(any(Genre.class));
	}
	// Test 4
	// Test Fail untuk save Genre
	@Test
	void failToUpdateGenre() throws Exception{
		// Mengkondisikan agar save Genre Gagal
		Genre genre = new Genre();
		genre.setNama("");
		// membandingkan apakah hasil dari save genre menghasilkan exception atau tidak
		assertThrows(Exception.class, ()->{genreService.update(1L,genre);});
		// memverifikasi apakah ada transaksi save dengan tipe object genre
		verify(genreRepository,never()).save(any(Genre.class));
	}
	// Test 5
	// Test untuk get All Genre
	@Test
	void getAllGenre() {
		// Mengkonndisikan data get All Genre
		List<Genre> genres = new ArrayList<Genre>();
		genres.add(new Genre("Horror1",new ArrayList<>()));
		genres.add(new Genre("Horror2",new ArrayList<>()));
		genres.add(new Genre("Horror3",new ArrayList<>()));
		given(genreRepository.findAll()).willReturn(genres);
		List<Genre> listGenres = genreRepository.findAll();
		// membandingkan apakah sama menghasilkan data list dari object genre atau tidak
		assertEquals(listGenres, genres);
	}
	
	// Test 6
	// Test Delete Genre
	@Test
	void deleteGenre() throws Exception {
		// Mengkondisikan untuk Delete Genre
		final Long genreId = 1L;
		genreService.softDelete(genreId);
		// Memverifikasi apakah perintah delete telah di lakukan atau tidak
		verify(genreRepository,times(1)).softDelete(genreId);
	}
	
}
