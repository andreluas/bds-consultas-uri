package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// SQL
		List<ProductMinProjection> list = repository.searchListProduct(10, 20, "P");
		List<ProductMinDTO> dtoList = list.stream().map(x -> new ProductMinDTO(x)).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ:");
		for (ProductMinDTO obj : dtoList) {
			System.out.println(obj);
		}

		System.out.println("\n\n");

		// JPQL
		List<ProductMinDTO> listJPQL = repository.searchListProductJPQL(10, 20, "P");

		System.out.println("\n*** RESULTADO JPQL:");
		for (ProductMinDTO obj : listJPQL) {
			System.out.println(obj);
		}
	}
}
