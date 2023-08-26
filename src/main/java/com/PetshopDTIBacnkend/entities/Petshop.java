package com.PetshopDTIBacnkend.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* Entidade de petshop, conforme discutido no README, a decisão foi implementar as 2 diferenças de preço como atributos. */
@Entity(name = "petshop")
@Table(name="petshop")
public class Petshop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Double kmDistance;
	
	private BigDecimal weekdayPriceSmallDog;
	
	private BigDecimal weekdayPriceBigDog;
	
	private BigDecimal weekendPriceSmallDog;
	
	private BigDecimal weekendPriceBigDog;
	
	public Petshop() {
	}

	public Petshop(Long id, String name, Double kmDistance, BigDecimal weekdayPriceSmallDog,
			BigDecimal weekdayPriceBigDog, BigDecimal weekendPriceSmallDog, BigDecimal weekendPriceBigDog) {
		this.id = id;
		this.name = name;
		this.kmDistance = kmDistance;
		this.weekdayPriceSmallDog = weekdayPriceSmallDog;
		this.weekdayPriceBigDog = weekdayPriceBigDog;
		this.weekendPriceSmallDog = weekendPriceSmallDog;
		this.weekendPriceBigDog = weekendPriceBigDog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getKmDistance() {
		return kmDistance;
	}

	public void setKmDistance(Double kmDistance) {
		this.kmDistance = kmDistance;
	}

	public BigDecimal getWeekdayPriceSmallDog() {
		return weekdayPriceSmallDog;
	}

	public void setWeekdayPriceSmallDog(BigDecimal weekdayPriceSmallDog) {
		this.weekdayPriceSmallDog = weekdayPriceSmallDog;
	}

	public BigDecimal getWeekdayPriceBigDog() {
		return weekdayPriceBigDog;
	}

	public void setWeekdayPriceBigDog(BigDecimal weekdayPriceBigDog) {
		this.weekdayPriceBigDog = weekdayPriceBigDog;
	}

	public BigDecimal getWeekendPriceSmallDog() {
		return weekendPriceSmallDog;
	}

	public void setWeekendPriceSmallDog(BigDecimal weekendPriceSmallDog) {
		this.weekendPriceSmallDog = weekendPriceSmallDog;
	}

	public BigDecimal getWeekendPriceBigDog() {
		return weekendPriceBigDog;
	}

	public void setWeekendPriceBigDog(BigDecimal weekendPriceBigDog) {
		this.weekendPriceBigDog = weekendPriceBigDog;
	}
	
	
	
}
