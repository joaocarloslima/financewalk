package br.com.fiap.financewalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.financewalk.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
}
