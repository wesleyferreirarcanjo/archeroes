package com.archeroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archeroes.domain.MetaHero;

@Repository
public interface MetaHeroRepository extends JpaRepository<MetaHero, Integer>{
	
	public List<MetaHero> findTop100By();
}
