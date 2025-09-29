package br.com.fiap.financewalk.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.model.TransactionFilters;
import br.com.fiap.financewalk.repository.TransactionRepository;
import br.com.fiap.financewalk.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(
        @RequestParam(required = false) String description,
        @RequestParam(required = false) LocalDate date
    ){
        log.info("Filtrando por descricao {} e data {}", description, date);
        var filters = new TransactionFilters(description, date);
        return transactionService.getTransactions(filters);
        
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody @Valid Transaction transaction){
        log.info("cadastrando transação" + transaction);
        return repository.save(transaction);
    }

}
