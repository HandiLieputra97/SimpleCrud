package com.handi.testCRUD.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
public class Utility {
	
	// Fungsi untuk mengambil tanggal system
	public Date getDateNow() {
		LocalDateTime today = LocalDateTime.now();  //Create date
		ZoneId id = ZoneId.of("Asia/Jakarta");  //Create timezone
		ZonedDateTime zdt = ZonedDateTime.of(today, id); 
		Date result = Date.from(zdt.toInstant());
		return result;
	}
}
