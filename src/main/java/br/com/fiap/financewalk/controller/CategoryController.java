package br.com.fiap.financewalk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.model.Category;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public List<Category> index(){ 
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Lazer", "Popcorn"));
        categories.add(new Category(2L, "Transporte", "Bus"));
        categories.add(new Category(3L, "Educação", "Book"));

        return categories;
    }
    
}
