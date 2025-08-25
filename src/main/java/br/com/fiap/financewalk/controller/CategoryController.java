package br.com.fiap.financewalk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.model.Category;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categories")
@Slf4j
public class CategoryController {

    private List<Category> repository = new ArrayList<>();

    @GetMapping
    public List<Category> index(){ 
        return repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category create(@RequestBody Category category){
        category.setId(Math.abs(new Random().nextLong()));
        log.info("criando categoria " + category);
        repository.add(category);
        return category;
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> get(@PathVariable Long id){
        log.info("buscando categoria com id " + id);
        var categoryFound = getCategoryById(id); 

        if (categoryFound.isEmpty()) return ResponseEntity.notFound().build(); 

        return ResponseEntity.ok(categoryFound.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        log.info("apagando categoria com id {}", id);

        var categoryFound = getCategoryById(id); 
        
        if (categoryFound.isEmpty()) return ResponseEntity.notFound().build(); 

        repository.remove(categoryFound.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@RequestBody Category categoryUpdated, @PathVariable Long id ){
        log.info("atualizando categoria {} com id {}", categoryUpdated, id);

        var categoryFound = getCategoryById(id); 
        
        if (categoryFound.isEmpty()) return ResponseEntity.notFound().build(); //TODO refatorar

        repository.remove(categoryFound.get());
        categoryUpdated.setId(id);
        repository.add(categoryUpdated);

        return ResponseEntity.ok(categoryUpdated);
    }

    private Optional<Category> getCategoryById(Long id) {
        var categoryFound = repository.stream()
            .filter(category -> category.getId().equals(id))
            .findFirst();
        return categoryFound;
    }

    
}
