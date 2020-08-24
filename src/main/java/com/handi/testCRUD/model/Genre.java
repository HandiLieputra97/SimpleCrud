package com.handi.testCRUD.model;

import java.util.List;

import javax.persistence.Entity;
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
// Model Genre dengan attribute nama
public class Genre extends BaseModel{
	
	private String nama;
	
	// relation dengan model buku
	@ManyToMany(mappedBy = "genres")
	List<Buku> bukus;
}
