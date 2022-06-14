package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// SQL
		List<EmpregadoDeptProjection> listEmpregados = repository.listEmpregados();
		List<EmpregadoDeptDTO> listEmpregadosDTO = listEmpregados.stream().map(x -> new EmpregadoDeptDTO(x))
				.collect(Collectors.toList());

		System.out.println("\n*** NATIVE SQL:");
		for (EmpregadoDeptDTO dto : listEmpregadosDTO) {
			System.out.println(dto);
		}
		System.out.println("\n\n");

		// JPQL
		List<EmpregadoDeptDTO> listEmpregadosJPQL = repository.listEmpregadosJPQL();

		System.out.println("\n*** JPQL:");
		for (EmpregadoDeptDTO dto : listEmpregadosJPQL) {
			System.out.println(dto);
		}
		System.out.println("\n\n");
	}
}
