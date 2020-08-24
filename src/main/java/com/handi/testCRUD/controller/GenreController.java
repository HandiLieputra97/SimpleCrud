package com.handi.testCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.handi.testCRUD.model.Genre;
import com.handi.testCRUD.repository.GenreRepository;
import com.handi.testCRUD.service.GenreService;

import javassist.expr.NewArray;


@Controller
@RequestMapping("/genre")
public class GenreController {
	
	// mendefinisikan/menyambungkan service untuk object genre
	@Autowired
    private GenreService genreService;
	
	// Controller untuk get All data Genre
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getAll(Model model)
	{
		// Mendefinisikan object genre baru untuk form create genre
		Genre genre = new Genre();
		// Mengambil data list Genre 
		List<Genre> genres = genreService.findAll();
		// Memasukan Title untuk Form Genre
		model.addAttribute("title","Add Genre");
		// Memasukan list genre kedalam variable genres
		model.addAttribute("genres", genres);
		// Memasukan object genre ke variable genre
		model.addAttribute("genre", genre);
		// Memanggil view Genre.html
		return "Genre";
	}
	// Controller untuk edit Genre (memasukan data genre ke form edit)
	@RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable Long id)
	{
		// Mencari Genre yang akan di edit
		Genre genre = genreService.findById(id);
		// Mengambil data list Genre
		List<Genre> genres = genreService.findAll();
		// Jika Genre yang mau diedit berhasil ditemukan
		if(genre != null)
		{
			// Memasukan object genre ke variable genre
			model.addAttribute("genre",genre);
			model.addAttribute("title","Edit Genre");
		}
		// Jika Genre yang mau di edit tidak berhasil ditemukan
		else {
			// Memasukan object genre yang baru ke variable genre
			model.addAttribute("genre", new Genre());
			model.addAttribute("title","Add Genre");
		}
		// Memasukan data list Genre ke variable genres
		model.addAttribute("genres", genres);
		// Memanggil view Genre.html
		return "Genre";
	}
	// Controller untuk delete Genre
	@RequestMapping(value="/delete/{id}")
	public String delete(Model model,@PathVariable Long id) throws Exception
	{
		// Mencari Object Genre yang akan di hapus
		Genre genre = genreService.findById(id);
		// Jika Genre yang akan di Delete berhasil ditemukan
		if(genre !=null)
		{
			// Menjalankan fungsi softDelete Genre
			genreService.softDelete(id);
		}
		// Me redirect ke /genre setelah fungsi delete berakhir
		return "redirect:/genre";
	}
	// Controller untuk Save / Update Genre
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String saveEdit(Genre genre) throws Exception
	{
		// Jika id dari genre tidak ada maka akan di save baru
		if(genre.getId() == null)
		{
			// Menjalankan fungsi Save Genre
			genreService.save(genre);
		}
		// Jika id dari genre ada maka akan di update
		else {
			// Menjalankan fungsi Update Genre
			genreService.update(genre.getId(), genre);
		}
		// Me redirect ke /genre setelah fungsi save/update berakhir
		return "redirect:/genre";
	}
	
	
}
