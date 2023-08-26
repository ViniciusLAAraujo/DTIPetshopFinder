package com.PetshopDTIBacnkend.services;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PetshopDTIBacnkend.dtos.BestPetShopDTO;
import com.PetshopDTIBacnkend.dtos.PetshopDTO;
import com.PetshopDTIBacnkend.dtos.SearchDTO;
import com.PetshopDTIBacnkend.entities.Petshop;
import com.PetshopDTIBacnkend.repositories.PetshopRepository;

import jakarta.persistence.EntityNotFoundException;


/* Serviço que realiza as pesquisas e o tratamento dos dados obtidos no banco. */
@Service
public class PetshopService {
	
	@Autowired
	private PetshopRepository petshopRepository;
	
	@Transactional(readOnly = true)
	public List<PetshopDTO> findAll(){
		List<Petshop> result = petshopRepository.findAll();
		return result.stream().map(x -> new PetshopDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public PetshopDTO findById(Long id) throws Exception{
		Optional<Petshop> result = petshopRepository.findById(id);
		if (!result.isPresent()){
			throw new EntityNotFoundException("Petshop não encontrado");
		}
		return new PetshopDTO(result.get());
	}
	

	/* Operação para receber por um DTO todos os dados presentes em um Petshop via JSON, 
	 * permitindo a alteração de um petshop existente ou a criação de um novo caso o 
	 * ID enviado seja válido ou ainda não exista. */
	@Transactional
	public PetshopDTO savePetshop(PetshopDTO petshopDTO) {
	    Petshop petshop = new Petshop();
	    petshop.setId(petshopDTO.getId());
	    petshop.setName(petshopDTO.getName());
	    petshop.setKmDistance(petshopDTO.getKmDistance());
	    petshop.setWeekdayPriceSmallDog(petshopDTO.getWeekdayPriceSmallDog());
	    petshop.setWeekdayPriceBigDog(petshopDTO.getWeekdayPriceBigDog());
	    petshop.setWeekendPriceSmallDog(petshopDTO.getWeekendPriceSmallDog());
	    petshop.setWeekendPriceBigDog(petshopDTO.getWeekendPriceBigDog());
	    
	    petshopRepository.save(petshop);
	    return new PetshopDTO(petshop);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception{
		Petshop result = petshopRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Petshop não encontrado"));		
		petshopRepository.deleteById(result.getId());
	}
	
	/* Segundo a data recebida, devolva se ela é um final de semana. */
	private Boolean isWeekendDay (LocalDate date) {
		 DayOfWeek dayOfWeek = date.getDayOfWeek();
	     return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}
	
	/* Função que recebe o petshop e devolve os dois preços unitários ajustados para o dia enviado pelo cliente. */
	private BigDecimal[] priceToPay(Petshop petshop, LocalDate date) {
		BigDecimal[] prices = new BigDecimal[2];
		if (isWeekendDay(date)) {
			BigDecimal smallDogPrice = (petshop.getWeekendPriceSmallDog() != null && petshop.getWeekendPriceSmallDog().compareTo(BigDecimal.ZERO) != 0) ? petshop.getWeekendPriceSmallDog(): petshop.getWeekdayPriceSmallDog();
			BigDecimal bigDogPrice = (petshop.getWeekendPriceBigDog() != null && petshop.getWeekendPriceSmallDog().compareTo(BigDecimal.ZERO) != 0) ? petshop.getWeekendPriceBigDog() : petshop.getWeekdayPriceBigDog();

			prices[0] = smallDogPrice;
			prices[1] = bigDogPrice;
	        return prices ;
		}
		else {
			BigDecimal smallDogPrice = petshop.getWeekdayPriceSmallDog();
			BigDecimal bigDogPrice = petshop.getWeekdayPriceBigDog();
			prices[0] = smallDogPrice;
			prices[1] = bigDogPrice;
	        return prices ;
		}
	}
	
	/* Função que, a partir de uma lista com todos os petshops, calcula o total a ser pago por banhos de cães pequenos 
	 * e grandes, de forma separada e conjunta. Em seguida, utiliza o Comparator na lista completa para ordenar por 
	 * preço e, em seguida, por distância (o algoritmo por trás dessa função é o MergeSort). */
	public List<BestPetShopDTO>  findBestPetshopList(SearchDTO searchDTO) {
		List<Petshop> petshops = petshopRepository.findAll();
		List<BestPetShopDTO> petshopToBestList = new ArrayList<BestPetShopDTO>();
		for(Petshop petshop : petshops){
			BigDecimal[] prices = priceToPay(petshop, searchDTO.getDate());
			BigDecimal smallDogAmount = BigDecimal.valueOf( searchDTO.getNumSmallDog() ).multiply( prices[0] );
			BigDecimal bigDogAmount = BigDecimal.valueOf( searchDTO.getNumBigDog() ).multiply( prices[1] );
			BigDecimal totalAmount = smallDogAmount.add(bigDogAmount);
			petshopToBestList.add(new BestPetShopDTO(petshop.getId(),petshop.getName(),petshop.getKmDistance(),totalAmount,smallDogAmount,bigDogAmount));
		}
		List<BestPetShopDTO> sortedBestList = petshopToBestList.stream()
			    .sorted(Comparator.comparing(BestPetShopDTO::getTotalAmount)
			        .thenComparing(BestPetShopDTO::getKmDistance))
			    .collect(Collectors.toList());
		return sortedBestList;
	}
	
	/* Função principal do desafio: a partir da lista ordenada dos melhores petshops, retorne o primeiro. */
	public BestPetShopDTO findBestPetshop (SearchDTO searchDTO) {
		return findBestPetshopList(searchDTO).get(0);
	}
	
	/* Operação necessária para inicializar os primeiros petshops. */
	public void saveInitialPetshop(Petshop petshop) {
		petshopRepository.save(petshop);
	}
}
