package com.avaliacao.sccon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaliacao.sccon.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
