package com.archeroes.resource;

import com.archeroes.domain.Hero;
import com.archeroes.service.HeroService;
import com.sipios.springsearch.anotation.SearchSpec;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/hero")
@Tag(name = "hero", description = "The Hero API")
public class HeroResource {

    @Autowired
    private HeroService service;

    @Operation(summary = "Encontra todos os Herois", description = "/admin/hero/all", tags = {"hero"})
    @GetMapping("/all")
    public ResponseEntity<List<Hero>> findAll() {
        List<Hero> list = this.service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Encontra Heroi por ID", description = "/admin/hero/{id}", tags = {"hero"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Heroi encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class))}),
            @ApiResponse(responseCode = "404", description = "Heroi nao encontrado", content = {@Content})})
    @GetMapping("/{id}")
    public ResponseEntity<Hero> find(@PathVariable Integer id) {
        Hero hero = service.find(id);
        return ResponseEntity.ok().body(hero);
    }

    @Operation(summary = "Encontra Heroi fazendo pesquisa", description = "/admin/hero/search=(param:value)", tags = {"hero"},
            parameters = {@Parameter(in = ParameterIn.QUERY, name = "search", description = "Query para pesquisar heroi", example = "igual(:), diferente(!), field:value")})
    @GetMapping
    public ResponseEntity<List<Hero>> search(@Parameter(hidden = true) @SearchSpec Specification<Hero> specs) {
        List<Hero> list = this.service.search(specs);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Cria um heroi", description = "/admin/hero/", tags = {"hero"})
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> insert(@Valid @RequestBody Hero obj) {
        Hero newObj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Edita um Heroi", description = "/admin/hero/{id}", tags = {"hero"})
    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Hero obj) {
        service.update(id, obj);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta um heroi", description = "/admin/hero/{id}", tags = {"hero"})
    @DeleteMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
