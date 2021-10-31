package com.devmsp.crud_Spring;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.devmsp.crud_Spring.rh.dominio.Pessoa;
import com.devmsp.crud_Spring.rh.dominio.PessoaRepositorio;

@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Pessoa p1 = new Pessoa("Joao");
		
		p1.setDataNascimento(LocalDate.of(1990, 4, 1));
		p1.setEmail("joao@gmail.com");
		//p1.setNome("Joao");
		
		Pessoa p2 = new Pessoa("Maria");
		p1.setDataNascimento(LocalDate.of(1900, 1, 1));
		p1.setEmail("Maria@gmail.com");
		
		pessoaRepositorio.save(p1);
		pessoaRepositorio.save(p2);
	}

}
