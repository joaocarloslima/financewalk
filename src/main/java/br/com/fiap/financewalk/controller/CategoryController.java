package br.com.fiap.financewalk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.model.Category;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CategoryController {

    private List<Category> repository = new ArrayList<>();

    @GetMapping("/categories")
    public List<Category> index(){ 
        return repository;
    }
    
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/categories")
    public Category create(@RequestBody Category category){
        category.setId(Math.abs(new Random().nextLong()));
        log.info("Criando categoria" + category);
        repository.add(category);
        return category;
    }

}
