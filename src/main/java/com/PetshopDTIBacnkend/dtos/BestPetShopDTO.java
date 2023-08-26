package com.PetshopDTIBacnkend.dtos;

import java.math.BigDecimal;


/* Controller que gerencia os endpoints, comunicando-se com os serviços e recebendo os pedidos do cliente via DTO
(por questões de segurança e para economia de recursos). */
public class BestPetShopDTO {
	private Long id;
	
	private String name;
	
	private Double kmDistance;
	
	private BigDecimal totalAmount;
	
	private BigDecimal smallDogAmount;
	
	private BigDecimal bigDogAmount;
	
	public BestPetShopDTO() {
		
	}

	public BestPetShopDTO(Long id, String name, Double kmDistance, BigDecimal totalAmount, BigDecimal smallDogAmount,
			BigDecimal bigDogAmount) {
		this.id = id;
		this.name = name;
		this.kmDistance = kmDistance;
		this.totalAmount = totalAmount;
		this.smallDogAmount = smallDogAmount;
		this.bigDogAmount = bigDogAmount;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public BigDecimal getSmallDogAmount() {
		return smallDogAmount;
	}

	public BigDecimal getBigDogAmount() {
		return bigDogAmount;
	}
	
	
}
