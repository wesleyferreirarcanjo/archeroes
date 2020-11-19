package com.archeroes.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.archeroes.domain.Hero;
import com.archeroes.repository.HeroRepository;
import com.archeroes.service.exception.DataIntegrityException;
import com.archeroes.service.exception.ObjectNotFoundException;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository repo;
	
	public List<Hero> findAll() {
		return repo.findAll();
	}
	
	public Hero find(Integer id) {
		
		Optional<Hero> hero = repo.findById(id);
		return hero.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Hero.class.getName())
				));
	}

	public List<Hero> search(Specification<Hero> specs) {
		return repo.findAll(Specification.where(specs));
	}
	
	@Transactional
	public Hero insert(Hero obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public void update(Integer id, Hero obj) {
		this.find(id);
		obj.setId(id);
		repo.save(obj);
	}

	public void delete(Integer id) {
		
		this.find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException  e) {
			throw new DataIntegrityException("nao e possivel excluir");
		}
		
	}

}
