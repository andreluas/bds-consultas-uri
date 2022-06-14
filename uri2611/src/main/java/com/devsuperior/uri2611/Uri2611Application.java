package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// SQL NATIVE
		List<MovieMinProjection> list = repository.searchMovieList("Action");
		List<MovieMinDTO> resultList = list.stream().map(x -> new MovieMinDTO(x)).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ:");
		for (MovieMinDTO obj : resultList) {
			System.out.println(obj);
		}

		System.out.println("\n\n");

		// JPQL
		System.out.println("\n*** RESULTADO JPQL:");
		List<MovieMinDTO> resultListJPQL = repository.searchMovieListJPQL("Action");
		for (MovieMinDTO obj : resultListJPQL) {
			System.out.println(obj);
		}
	}
}