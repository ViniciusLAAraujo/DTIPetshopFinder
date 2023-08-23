package com.PetshopDTIBacnkend.services;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PetshopDTIBacnkend.dtos.PetshopDTO;
import com.PetshopDTIBacnkend.entities.Petshop;
import com.PetshopDTIBacnkend.repositories.PetshopRepository;



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
	public PetshopDTO findById(Long id){
		Petshop result = petshopRepository.findById(id).get();			
		return new PetshopDTO(result);
	}
	
	private void savePetshop(Petshop petshop) {
		this.petshopRepository.save(petshop);
	}
	
	public Boolean isWeekendDay (LocalDate date) {
		 DayOfWeek dayOfWeek = date.getDayOfWeek();
	     return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}
	
	public BigDecimal[] priceToPay(Petshop petshop, LocalDate date) {
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
}
