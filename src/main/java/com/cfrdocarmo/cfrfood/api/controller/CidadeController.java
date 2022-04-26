package com.cfrdocarmo.cfrfood.api.controller;

import java.util.List;

import com.cfrdocarmo.cfrfood.api.assembler.CidadeInputDisassembler;
import com.cfrdocarmo.cfrfood.api.assembler.CidadeModelAssembler;
import com.cfrdocarmo.cfrfood.api.model.CidadeModel;
import com.cfrdocarmo.cfrfood.api.model.input.CidadeInput;
import com.cfrdocarmo.cfrfood.domain.exception.EstadoNaoEncontradoException;
import com.cfrdocarmo.cfrfood.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cfrdocarmo.cfrfood.domain.model.Cidade;
import com.cfrdocarmo.cfrfood.domain.repository.CidadeRepository;
import com.cfrdocarmo.cfrfood.domain.service.CadastroCidadeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscarPorId(@PathVariable Long cidadeId){
		return cidadeModelAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput){
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
		}  catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput){

		try{
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId){
		cadastroCidade.excluir(cidadeId);
	}


	
}
