package com.archeroes.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archeroes.domain.Hero;
import com.archeroes.service.HeroService;
import com.sipios.springsearch.anotation.SearchSpec;


@RestController
@RequestMapping("/hero")
public class HeroResource {

	@Autowired
	private HeroService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Hero>> findAll() {
		List<Hero> list = this.service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hero> find(@PathVariable Integer id) {
		Hero hero = service.find(id);
		return ResponseEntity.ok().body(hero);
	}
	
	@GetMapping
	public ResponseEntity<List<Hero>> search(@SearchSpec Specification<Hero> specs) {
		List<Hero> list = this.service.search(specs);
		return ResponseEntity.ok().body(list);
	}
	

}
