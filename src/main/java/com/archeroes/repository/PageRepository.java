package com.archeroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archeroes.domain.MetaHero;

@Repository
public interface PageRepository extends JpaRepository<MetaHero, Integer>{

}
