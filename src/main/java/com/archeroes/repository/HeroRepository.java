package com.archeroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.archeroes.domain.Hero;

@RepositoryRestResource
public interface HeroRepository extends JpaRepository<Hero, Integer>, JpaSpecificationExecutor<Hero>{

}
