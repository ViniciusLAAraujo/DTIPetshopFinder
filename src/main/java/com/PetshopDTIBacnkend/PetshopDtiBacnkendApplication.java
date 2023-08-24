package com.PetshopDTIBacnkend;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.PetshopDTIBacnkend.entities.Petshop;
import com.PetshopDTIBacnkend.services.PetshopService;

@SpringBootApplication
public class PetshopDtiBacnkendApplication implements ApplicationRunner{
	
	@Autowired
	PetshopService petshopService;
	
	public static void main(String[] args) {
		SpringApplication.run(PetshopDtiBacnkendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Petshop meuCaninoFeliz = new Petshop(1L,  "Meu Canino Feliz",  2.0, BigDecimal.valueOf(20.00),
				BigDecimal.valueOf(40.00),  BigDecimal.valueOf(24.00),  BigDecimal.valueOf(48.00)) ;
		Petshop vaiRex = new Petshop(2L,  "Vai Rex",  1.7, BigDecimal.valueOf(15.00),
				BigDecimal.valueOf(50.00),  BigDecimal.valueOf(20.00),  BigDecimal.valueOf(55.00)) ;
		Petshop chowChawgas = new Petshop(3L,  "ChowChawgas",  0.8, BigDecimal.valueOf(30.00),
				BigDecimal.valueOf(45.00),  BigDecimal.valueOf(30.00),  BigDecimal.valueOf(45.00)) ;
		
		Arrays.asList(meuCaninoFeliz,vaiRex,chowChawgas).stream()
				.forEach(petshop -> petshopService.saveInitialPetshop(petshop));
		
	}	
	

}
