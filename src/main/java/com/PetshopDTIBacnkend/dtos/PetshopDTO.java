package com.PetshopDTIBacnkend.dtos;

import java.math.BigDecimal;

import com.PetshopDTIBacnkend.entities.Petshop;

public class PetshopDTO {
	private Long id;
	
	private String name;
	
	private Double kmDistance;
	
	private BigDecimal weekdayPriceSmallDog;
	
	private BigDecimal weekdayPriceBigDog;
	
	private BigDecimal weekendPriceSmallDog;
	
	private BigDecimal weekendPriceBigDog;
	
	public PetshopDTO() {
	}

	public PetshopDTO(Long id, String name, Double kmDistance, BigDecimal weekdayPriceSmallDog,
	         BigDecimal weekdayPriceBigDog, BigDecimal weekendPriceSmallDog, BigDecimal weekendPriceBigDog) {
	    this.id = id;
	    this.name = name;
	    this.kmDistance = kmDistance;
	    this.weekdayPriceSmallDog = weekdayPriceSmallDog;
	    this.weekdayPriceBigDog = weekdayPriceBigDog;
	    this.weekendPriceSmallDog = weekendPriceSmallDog;
	    this.weekendPriceBigDog = weekendPriceBigDog;
	}
	
	public PetshopDTO(Petshop petshop) {
		this.id = petshop.getId();
		this.name = petshop.getName();
        this.kmDistance = petshop.getKmDistance();
        this.weekdayPriceSmallDog = petshop.getWeekdayPriceSmallDog();
        this.weekdayPriceBigDog = petshop.getWeekdayPriceBigDog();
        this.weekendPriceSmallDog = petshop.getWeekendPriceSmallDog();
        this.weekendPriceBigDog = petshop.getWeekendPriceBigDog();
	}
	
	


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getKmDistance() {
		return kmDistance;
	}

	public BigDecimal getWeekdayPriceSmallDog() {
		return weekdayPriceSmallDog;
	}

	public BigDecimal getWeekdayPriceBigDog() {
		return weekdayPriceBigDog;
	}

	public BigDecimal getWeekendPriceSmallDog() {
		return weekendPriceSmallDog;
	}

	public BigDecimal getWeekendPriceBigDog() {
		return weekendPriceBigDog;
	}
	
	
}
