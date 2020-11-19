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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/admin/hero")
@Tag(name = "hero", description = "The Hero API")
public class HeroResource {

	@Autowired
	private HeroService service;
	

	@Operation(summary = "Encontra todos os Herois", description = "/admin/hero/all", tags = { "hero" })
	@GetMapping("/all")
	public ResponseEntity<List<Hero>> findAll() {
		List<Hero> list = this.service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Encontra Heroi por ID", description = "/admin/hero/{id}", tags = { "hero" })
	@GetMapping("/{id}")
	public ResponseEntity<Hero> find(@PathVariable Integer id) {
		Hero hero = service.find(id);
		return ResponseEntity.ok().body(hero);
	}
	
	@Operation(summary = "Encontra Heroi fazendo pesquisa", description = "/admin/hero/search=(param:value)", tags = { "hero" })
	@GetMapping
	public ResponseEntity<List<Hero>> search(@SearchSpec Specification<Hero> specs) {
		List<Hero> list = this.service.search(specs);
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Cria um heroi", description = "/admin/hero/", tags = { "hero" })
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Hero obj) {
		Hero newObj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Edita um Heroi", description = "/admin/hero/{id}", tags = { "hero" })
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody Hero obj) {
		service.update(id, obj);
		return ResponseEntity.noContent().build();	
	}
	
	@Operation(summary = "Deleta um heroi", description = "/admin/hero/{id}", tags = { "hero" })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
