package com.PetshopDTIBacnkend.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.PetshopDTIBacnkend.dtos.BestPetShopDTO;
import com.PetshopDTIBacnkend.dtos.SearchDTO;
import com.PetshopDTIBacnkend.entities.Petshop;
import com.PetshopDTIBacnkend.repositories.PetshopRepository;


/* Teste para principal funcionalidade do desafio */
@ExtendWith(MockitoExtension.class)
public class PetshopServiceTest {
	
	/* @Mock e @InjectMocks do Mockito permite simular o comportamento do repositório PetshopRepository, garantindo 
	 * que os testes sejam isolados e não dependam do banco de dados.*/
	
	@InjectMocks
	PetshopService petshopService; //Injeta as simulações nas dependencias da classe
	
	@Mock
	PetshopRepository petshopRepository; //Cria a dependencia da classe

	
	/* Cada um dos três testes a seguir verifica se, para o banco de dados base e os parâmetros de pesquisa dados, 
	 * o nome do petshop selecionado como o melhor é o correto, com base no critério de melhor preço e proximidade. */

	@Test
	public void findBestPetshopTestShouldChowChawgasBest () {
		SearchDTO paramChowChawgas= new SearchDTO(LocalDate.of(2023, 8, 26),2,4);
		
		Petshop meuCaninoFeliz = new Petshop(1L,  "Meu Canino Feliz",  2.0, BigDecimal.valueOf(20.00),
				BigDecimal.valueOf(40.00),  BigDecimal.valueOf(24.00),  BigDecimal.valueOf(48.00)) ;
		Petshop vaiRex = new Petshop(2L,  "Vai Rex",  1.7, BigDecimal.valueOf(15.00),
				BigDecimal.valueOf(50.00),  BigDecimal.valueOf(20.00),  BigDecimal.valueOf(55.00)) ;
		Petshop chowChawgas = new Petshop(3L,  "ChowChawgas",  0.8, BigDecimal.valueOf(30.00),
				BigDecimal.valueOf(45.00),  BigDecimal.valueOf(30.00),  BigDecimal.valueOf(45.00)) ;
		
		List<Petshop> petshopList = Arrays.asList(meuCaninoFeliz,vaiRex,chowChawgas);
		
		Mockito.when(petshopRepository.findAll())
		.thenReturn(petshopList);
		
		BestPetShopDTO result = petshopService.findBestPetshop(paramChowChawgas);
		
		Assertions.assertEquals(result.getName(),"ChowChawgas");
		
	}
	
	@Test
	public void findBestPetshopTestShouldVilaHexBest () {
		SearchDTO paramVaiHex = new SearchDTO(LocalDate.of(2023, 8, 23),2,1);

		Petshop meuCaninoFeliz = new Petshop(1L,  "Meu Canino Feliz",  2.0, BigDecimal.valueOf(20.00),
				BigDecimal.valueOf(40.00),  BigDecimal.valueOf(24.00),  BigDecimal.valueOf(48.00)) ;
		Petshop vaiRex = new Petshop(2L,  "Vai Rex",  1.7, BigDecimal.valueOf(15.00),
				BigDecimal.valueOf(50.00),  BigDecimal.valueOf(20.00),  BigDecimal.valueOf(55.00)) ;
		Petshop chowChawgas = new Petshop(3L,  "ChowChawgas",  0.8, BigDecimal.valueOf(30.00),
				BigDecimal.valueOf(45.00),  BigDecimal.valueOf(30.00),  BigDecimal.valueOf(45.00)) ;
		
		List<Petshop> petshopList = Arrays.asList(meuCaninoFeliz,vaiRex,chowChawgas);
		
		Mockito.when(petshopRepository.findAll())
		.thenReturn(petshopList);
		
		BestPetShopDTO result = petshopService.findBestPetshop(paramVaiHex);
		
		Assertions.assertEquals(result.getName(),"Vai Rex");

		
	}
	
	@Test
	public void findBestPetshopTestShouldMeuCaninoFelizBest () {
		SearchDTO paramMeuCaninoFeliz= new SearchDTO(LocalDate.of(2023, 8, 27),2,2);

		Petshop meuCaninoFeliz = new Petshop(1L,  "Meu Canino Feliz",  2.0, BigDecimal.valueOf(20.00),
				BigDecimal.valueOf(40.00),  BigDecimal.valueOf(24.00),  BigDecimal.valueOf(48.00)) ;
		Petshop vaiRex = new Petshop(2L,  "Vai Rex",  1.7, BigDecimal.valueOf(15.00),
				BigDecimal.valueOf(50.00),  BigDecimal.valueOf(20.00),  BigDecimal.valueOf(55.00)) ;
		Petshop chowChawgas = new Petshop(3L,  "ChowChawgas",  0.8, BigDecimal.valueOf(30.00),
				BigDecimal.valueOf(45.00),  BigDecimal.valueOf(30.00),  BigDecimal.valueOf(45.00)) ;
		
		List<Petshop> petshopList = Arrays.asList(meuCaninoFeliz,vaiRex,chowChawgas);
		
		Mockito.when(petshopRepository.findAll())
		.thenReturn(petshopList);
		
		BestPetShopDTO result = petshopService.findBestPetshop(paramMeuCaninoFeliz);
		
		Assertions.assertEquals(result.getName(),"Meu Canino Feliz");
		
	}
	
}
