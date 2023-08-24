package com.PetshopDTIBacnkend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PetshopDTIBacnkend.dtos.BestPetShopDTO;
import com.PetshopDTIBacnkend.dtos.PetshopDTO;
import com.PetshopDTIBacnkend.dtos.SearchDTO;
import com.PetshopDTIBacnkend.services.PetshopService;


@RestController
@RequestMapping(value = "/petshop")
@CrossOrigin(origins = "*")
public class PetshopController {
	@Autowired
	private PetshopService petshopService;
	
	@GetMapping
	public ResponseEntity<List<PetshopDTO>>  findAll(){
		List<PetshopDTO> result = petshopService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/{petshopId}")
	public ResponseEntity<PetshopDTO>  findPetshopById(@PathVariable Long petshopId) throws Exception{
		PetshopDTO result = petshopService.findById(petshopId);
		return new ResponseEntity<>(result, HttpStatus.OK) ;
	}
	
	@PostMapping(value = "/upsert")
	public ResponseEntity<PetshopDTO> savePetshop(@RequestBody PetshopDTO petshopDTO){
		PetshopDTO upsertPetshop = petshopService.savePetshop(petshopDTO);
		return new ResponseEntity<>(upsertPetshop, HttpStatus.CREATED) ;
	}
	@DeleteMapping(value = "/delete/{petshopId}")
	public ResponseEntity<PetshopDTO> deletePetshop(@PathVariable Long petshopId) throws Exception{
		this.petshopService.deleteById(petshopId);
		return new ResponseEntity<>(null, HttpStatus.OK) ;
	}
	
	@PostMapping(value = "/best")
	public ResponseEntity<BestPetShopDTO>  findBest(@RequestBody SearchDTO searchDTO){
		BestPetShopDTO result = petshopService.findBestPetshop(searchDTO);
		return new ResponseEntity<>(result, HttpStatus.OK) ;
	}
	
	@PostMapping(value = "/bestlist")
	public ResponseEntity<List<BestPetShopDTO>>  findBestList(@RequestBody SearchDTO searchDTO){
		List<BestPetShopDTO> result = petshopService.findBestPetshopList(searchDTO);
		return new ResponseEntity<>(result, HttpStatus.OK) ;
	}

}
