package com.cfrdocarmo.cfrfood.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

//	@Autowired
//	private CozinhaRepository cozinhaRepository;
//	
//	@Autowired
	//private RestauranteRepository restauranteRepository;
	
//	@ResponseStatus(code = HttpStatus.ACCEPTED)
//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> cozinhasPorNome(String nome){
//		return ((CozinhaRepository) cozinhaRepository).findTodasByNomeContaining(nome);	
//	}
//	
//	@GetMapping("/restaurantes/por-taxa-frete")
//	public List<Restaurante> restaurantePorTaxaFrete(BigDecimal taxaIncial, BigDecimal taxaFinal){
//		return restauranteRepository.findByTaxaFreteBetween(taxaIncial, taxaFinal);
//	}
//	
//	@GetMapping("/restaurantes/por-nome")
//	public List<Restaurante> restaurantePorTaxaFrete(String nome, Long cozinhaId){
//		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
//	}
}
