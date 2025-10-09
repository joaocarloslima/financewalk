package br.com.fiap.financewalk.controller;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.repository.CategoryRepository;
import br.com.fiap.financewalk.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categories")
@Slf4j
public class CategoryController {

    @Autowired // IoD
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> index() {
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody @Valid Category category) {
        log.info("criando categoria " + category);
        return categoryService.save(category);
    }

    @GetMapping("{id}")
    public Category get(@PathVariable Long id) {
        log.info("buscando categoria com id " + id);
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria com id {}", id);
        categoryService.deleteById(id);
    }

    @PutMapping("{id}")
    public Category update(@RequestBody @Valid Category categoryUpdated, @PathVariable Long id) {
        log.info("atualizando categoria {} com id {}", categoryUpdated, id);
        return categoryService.update(categoryUpdated, id);
    }

   

}
