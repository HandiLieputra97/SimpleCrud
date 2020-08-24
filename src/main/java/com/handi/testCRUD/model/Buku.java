package com.handi.testCRUD.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// model Buku yang terdapat attribute isbn,judul, penulis, penerbit, tahun terbit, sinopsis
public class Buku extends BaseModel{
	
	private String isbn;
	private String judul;
	private String penulis;
	private String penerbit;
	private String tahunTerbit;
	// column definition text agar dapat melebihi batas karakter 255
	@Column(columnDefinition = "TEXT") 
	private String sinopsis;
	
	// Relation dengan model genre many to many dengan fetchtype eager mengambil semua data terlebih dahulu
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "buku_genre",
            joinColumns = @JoinColumn(name = "buku_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

}