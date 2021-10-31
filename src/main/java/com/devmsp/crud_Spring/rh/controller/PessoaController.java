package com.devmsp.crud_Spring.rh.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devmsp.crud_Spring.rh.dominio.Pessoa;
import com.devmsp.crud_Spring.rh.dominio.PessoaRepositorio;

@Controller
public class PessoaController {

	private PessoaRepositorio pessoaRepositorio;
	
	public PessoaController(PessoaRepositorio pessoaRepositorio) {
		this.pessoaRepositorio = pessoaRepositorio;
	}
	
	@GetMapping("/rh/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepositorio.findAll());
		return "rh/pessoas/index";
	}
	
	@GetMapping("/rh/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "rh/pessoas/form";
		
	}
	
	@GetMapping("/rh/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id")long id, Model model) {
		
		Optional<Pessoa> pessoaOpt = pessoaRepositorio.findById(id);
		if(pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		model.addAttribute("pessoa",pessoaOpt.get());
		return "rh/pessoas/form";
	}
	
	@PostMapping("/rh/pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa")Pessoa pessoa) {
		pessoaRepositorio.save(pessoa);
		return "redirect:/rh/pessoas";
	}
	
	
	@GetMapping("rh/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaOpt = pessoaRepositorio.findById(id);
		if(pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa invalida.");
		}
		pessoaRepositorio.delete(pessoaOpt.get());
		return "redirect:/rh/pessoas";
	}
	
}
