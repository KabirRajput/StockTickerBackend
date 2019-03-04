package com.legend.stock.dbservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.stock.dbservice.model.Quotes;
import com.legend.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceApplication {

	@Autowired
	private QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		return getQuotesByUsername(username);
	}
	
	private List<String> getQuotesByUsername(@PathVariable("username") final String username) {
		return quotesRepository.findByUsername(username)
				.stream()
				.map(quote -> {
					return quote.getQuote();
				})
			.collect(Collectors.toList());
	}
	
	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
		quotes.getQuotes()
			.stream()
			.map(quote -> new Quote(quotes.getUsername(), quote))
			.forEach(quote -> {
				quotesRepository.save(quote));
			});
		return null;
	}
}
