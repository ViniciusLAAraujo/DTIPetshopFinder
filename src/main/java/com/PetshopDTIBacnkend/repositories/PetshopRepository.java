package com.PetshopDTIBacnkend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PetshopDTIBacnkend.entities.Petshop;

/* Segundo implementação do JPARepository: o mapeamento e os acessos simples de nível CRUD 
 * ao banco de dados são realizados automaticamente por essa interface. */
public interface PetshopRepository extends JpaRepository<Petshop, Long>{

}
