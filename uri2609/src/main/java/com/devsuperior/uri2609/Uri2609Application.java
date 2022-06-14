package com.devsuperior.uri2609;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import com.devsuperior.uri2609.repositories.CategoryRepository;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// SQL NATIVE
		List<CategorySumProjection> listCategorySum = repository.listCategorySum();
		List<CategorySumDTO> listDTO = listCategorySum.stream().map(x -> new CategorySumDTO(x))
				.collect(Collectors.toList());

		System.out.println("*** RESULTADO SQL NATIVE");
		for (CategorySumDTO dto : listDTO) {
			System.out.println(dto);
		}

		System.out.println("\n\n");

		System.out.println("*** RESULTADO JPQL");
		List<CategorySumDTO> listJPQL = repository.listCategorySumJPQL();
		for (CategorySumDTO dto : listJPQL) {
			System.out.println(dto);
		}

	}
}
