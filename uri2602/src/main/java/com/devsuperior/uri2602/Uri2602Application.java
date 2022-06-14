package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<CustomerMinProjection> list = repository.searchSql("rs");
		List<CustomerMinDTO> resultSql = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());

		System.out.println("### RESULTADO SQL NATIVE ###");
		for (CustomerMinDTO obj : resultSql) {
			System.out.println(obj);
		}

		System.out.println("\n\n");

		List<CustomerMinDTO> resultJpql = repository.searchJpql("rs");

		System.out.println("### RESULTADO JPQL ###");
		for (CustomerMinDTO obj : resultJpql) {
			System.out.println(obj);
		}
	}
}
