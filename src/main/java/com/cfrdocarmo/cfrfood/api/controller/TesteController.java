package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.domain.model.Cozinha;
import com.cfrdocarmo.cfrfood.domain.model.Restaurante;
import com.cfrdocarmo.cfrfood.domain.repository.CozinhaRepository;
import com.cfrdocarmo.cfrfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return ((CozinhaRepository) cozinhaRepository).findTodasByNomeContaining(nome);
	}

//	@GetMapping("/restaurantes/por-taxa-frete")
//	public List<Restaurante> restaurantePorTaxaFrete(BigDecimal taxaIncial, BigDecimal taxaFinal){
//		return restauranteRepository.findByTaxaFreteBetween(taxaIncial, taxaFinal);
//	}
//
//	@GetMapping("/restaurantes/por-nome")
//	public List<Restaurante> restaurantePorTaxaFrete(String nome, Long cozinhaId){
//
//
//
//		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
//	}
}
