package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.api.assembler.FormaPagamentoAssembler;
import com.cfrdocarmo.cfrfood.api.assembler.RestauranteInputDisassembler;
import com.cfrdocarmo.cfrfood.api.assembler.RestauranteModelAssembler;
import com.cfrdocarmo.cfrfood.api.model.FormaPagamentoModel;
import com.cfrdocarmo.cfrfood.api.model.RestauranteModel;
import com.cfrdocarmo.cfrfood.api.model.input.RestauranteInput;
import com.cfrdocarmo.cfrfood.api.openapi.controller.RestaurantesFormasPagamentoControllerOpenApi;
import com.cfrdocarmo.cfrfood.domain.exception.CidadeNaoEncontradaException;
import com.cfrdocarmo.cfrfood.domain.exception.CozinhaNaoEncontradaException;
import com.cfrdocarmo.cfrfood.domain.exception.NegocioException;
import com.cfrdocarmo.cfrfood.domain.model.Restaurante;
import com.cfrdocarmo.cfrfood.domain.repository.RestauranteRepository;
import com.cfrdocarmo.cfrfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestaurantesFormasPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		return formaPagamentoAssembler.toCollectionModel(restaurante.getFormasPagamento());
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
