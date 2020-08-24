package com.handi.testCRUD.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
// Base model yang terdapat attribute id, createdDate, modifiedDate, & deleted
// agar attribute-attribute tersebut tidak dibuat ulang di setiap model
public abstract class BaseModel {
	// ID primary key dengan tipe Long
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	// Created Date saat pertama kali object baru dibuat
	@CreationTimestamp
	@Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

	// Modified Date saat ada update ke object
    @Version
    @UpdateTimestamp
	@Column(name="modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    // Untuk soft delete
    private boolean deleted;
	
}
