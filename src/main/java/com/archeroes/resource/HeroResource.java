package com.archeroes.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.archeroes.domain.Hero;
import com.archeroes.service.HeroService;
import com.sipios.springsearch.anotation.SearchSpec;


@RestController
@RequestMapping("/admin/hero")
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
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Hero obj) {
		Hero newObj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody Hero obj) {
		service.update(id, obj);
		return ResponseEntity.noContent().build();	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
