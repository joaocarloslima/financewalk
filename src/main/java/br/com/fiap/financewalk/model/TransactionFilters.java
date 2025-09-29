package br.com.fiap.financewalk.model;

import java.time.LocalDate;

public record TransactionFilters(
    String description,
    LocalDate date
) {}
