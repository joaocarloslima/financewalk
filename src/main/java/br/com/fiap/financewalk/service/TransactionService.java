package br.com.fiap.financewalk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.model.TransactionFilters;
import br.com.fiap.financewalk.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getTransactions(TransactionFilters filters) {
        var probe = Transaction.builder()
            .description(filters.description())
            .date(filters.date())
            .build();

        var matcher = ExampleMatcher.matchingAll()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(probe, matcher);

        return repository.findAll(example);
    }
    
}
