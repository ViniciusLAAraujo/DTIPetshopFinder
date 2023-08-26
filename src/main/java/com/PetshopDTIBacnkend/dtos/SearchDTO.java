package com.PetshopDTIBacnkend.dtos;

import java.time.LocalDate;

/* DTO que recebe apenas os parâmetros para pesquisa. */
public class SearchDTO {
	
	private LocalDate date;
	
	private Integer numSmallDog;
	
	private Integer numBigDog ;
	
	public SearchDTO () {}

	public SearchDTO(LocalDate date, Integer numSmallDog, Integer numBigDog) {
		this.date = date;
		this.numSmallDog = numSmallDog;
		this.numBigDog = numBigDog;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getNumSmallDog() {
		return numSmallDog;
	}

	public Integer getNumBigDog() {
		return numBigDog;
	}
	
	
}
