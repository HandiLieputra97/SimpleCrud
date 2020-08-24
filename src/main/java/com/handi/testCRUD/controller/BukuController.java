package com.handi.testCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.handi.testCRUD.model.Buku;
import com.handi.testCRUD.model.Genre;
import com.handi.testCRUD.service.BukuService;
import com.handi.testCRUD.service.GenreService;

@Controller
public class BukuController {
	
	// mendefinisikan/menyambungkan service untuk object Buku
	@Autowired
	private BukuService bukuService;
	
	// mendefinisikan/menyambungkan service untuk object Genre
	@Autowired
	private GenreService genreService;

	// Controller untuk get All data Buku
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getAll(Model model)
	{
		// Mendefinisikan object Buku baru untuk form create Buku
		Buku buku = new Buku();
		// Mengambil data list Buku
		List<Buku> bukus = bukuService.findAll();
		// Mengambil data list Genre
		List<Genre> genres = genreService.findAll();
		// Memasukan Title untuk Form Buku
		model.addAttribute("title","Add Book");
		// Memasukan data list Buku ke variable bukus
		model.addAttribute("bukus", bukus);
		// Memasukan data Buku baru ke variable buku
		model.addAttribute("buku", buku);
		// Memasukan data list Genre ke variable genres
		model.addAttribute("listGenre", genres);
		// Memanggil view Buku.html
		return "Buku";
	}
	// Controller untuk edit data Buku (memasukan data buku ke form edit)
	@RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable Long id)
	{
		// Mencari Buku yang akan di edit
		Buku buku = bukuService.findById(id);
		// Mengambil data list Buku
		List<Buku> bukus = bukuService.findAll();
		// Mengambil data list Genre
		List<Genre> genres = genreService.findAll();
		// Jika Buku yang mau diedit berhasil ditemukan
		if(buku != null)
		{
			// Memasukan object Buku ke variable Buku
			model.addAttribute("buku",buku);
			model.addAttribute("title","Edit Book");
		}
		// Jika Genre yang mau diedit tidak berhasil ditemukan
		else {
			// Memasukan object Buku yang baru ke variable Buku
			model.addAttribute("buku", new Buku());
			model.addAttribute("title","Add Book");
		}
		// Memasukan data list Buku ke variable bukus
		model.addAttribute("bukus", bukus);
		// Memasukan data list Genre ke variable genres
		model.addAttribute("listGenre", genres);
		// Memanggil view Buku.html
		return "Buku";
	}
	// Controller untuk delete Buku
	@RequestMapping(value="/delete/{id}")
	public String delete(Model model,@PathVariable Long id) throws Exception
	{
		// Mencari Object Buku yang akan di hapus
		Buku buku = bukuService.findById(id);
		// Jika Buku yang akan di Delete berhasil ditemukan
		if(buku !=null)
		{
			// Menjalankan fungsi softDelete Buku
			bukuService.softDelete(id);
		}
		// Me redirect ke halaman awal buku setelah fungsi delete berakhir
		return "redirect:/";
	}
	// Controller untuk Save / Update Buku
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String saveEdit(Buku buku) throws Exception
	{
		// Jika id dari Buku tidak ada maka akan di save baru
		if(buku.getId() == null)
		{
			// Menjalankan fungsi Save Buku
			bukuService.save(buku);
		}
		// Jika id dari Buku ada maka akan di update
		else {
			// Menjalankan fungsi Update Buku
			bukuService.update(buku.getId(), buku);
		}
		// Me redirect ke halaman awal buku setelah fungsi Save/update berakhir
		return "redirect:/";
	}
	
	
	
}
