package com.archeroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archeroes.domain.Hero;

public interface HeroRepository extends JpaRepository<Hero, Integer>{

}
