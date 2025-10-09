package br.com.fiap.financewalk.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.repository.CategoryRepository;

@Service
@CacheConfig(cacheNames = "categories")
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private ChatClient chat;

    public CategoryService(ChatClient.Builder chatBuilder) {
        this.chat = chatBuilder.build();
    }

    @CacheEvict(allEntries = true)
    public Category save(Category category) {
        if (category.getIcon().isEmpty()) {
            String icon = chat
                    .prompt("indique um ícone do Lucide.dev para a categoria " + category.getName()
                            + ". Retorne apenas o nome do ícone com a primeira letra maiuscula.")
                    .call()
                    .content();

            category.setIcon(icon);
        }
        return categoryRepository.save(category);
    }

    @Cacheable
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        var category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Cacheable
    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com id " + id));
    }

    @CacheEvict(allEntries = true)
    public Category update(Category categoryUpdated, Long id) {
        getCategoryById(id);
        categoryUpdated.setId(id);
        return categoryRepository.save(categoryUpdated);
    }

}
