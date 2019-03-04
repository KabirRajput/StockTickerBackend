package com.legend.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legend.stock.dbservice.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
	List<Quote> findByUsername(String username);
}
