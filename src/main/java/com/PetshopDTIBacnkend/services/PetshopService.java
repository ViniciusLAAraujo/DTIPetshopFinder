package com.PetshopDTIBacnkend.services;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PetshopDTIBacnkend.dtos.BestPetShopDTO;
import com.PetshopDTIBacnkend.dtos.PetshopDTO;
import com.PetshopDTIBacnkend.dtos.SearchDTO;
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
	public PetshopDTO findById(Long id) throws Exception{
		Petshop result = petshopRepository.findById(id).orElseThrow(() -> new Exception("Petshop não encontrado"));			
		return new PetshopDTO(result);
	}
	
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
		Petshop result = petshopRepository.findById(id).orElseThrow(() -> new Exception("Petshop não encontrado"));			
		petshopRepository.deleteById(result.getId());
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
//		List<BestPetShopDTO> sortedBestList = petshopToBestList.stream()
//                .sorted((pets1, pets2) -> pets1.getTotalAmount().compareTo(pets2.getBigDogAmount()))
//                .collect(Collectors.toList());
		return sortedBestList;
	}
	
	public BestPetShopDTO findBestPetshop (SearchDTO searchDTO) {
		return findBestPetshopList(searchDTO).get(0);
	}
}
