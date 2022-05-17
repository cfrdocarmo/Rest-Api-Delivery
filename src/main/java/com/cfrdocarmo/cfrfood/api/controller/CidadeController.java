package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.api.assembler.CidadeInputDisassembler;
import com.cfrdocarmo.cfrfood.api.assembler.CidadeModelAssembler;
import com.cfrdocarmo.cfrfood.api.model.CidadeModel;
import com.cfrdocarmo.cfrfood.api.model.input.CidadeInput;
import com.cfrdocarmo.cfrfood.domain.exception.EstadoNaoEncontradoException;
import com.cfrdocarmo.cfrfood.domain.exception.NegocioException;
import com.cfrdocarmo.cfrfood.domain.model.Cidade;
import com.cfrdocarmo.cfrfood.domain.repository.CidadeRepository;
import com.cfrdocarmo.cfrfood.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
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

	@ApiOperation("Lista as cidades")
	@GetMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}

	@ApiOperation("Busca uma cidade por Id")
	@GetMapping("/{cidadeId}")
	public CidadeModel buscarPorId(@ApiParam(value = "ID de uma Cidade", example = "1") @PathVariable Long cidadeId){
		return cidadeModelAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
	}

	@ApiOperation("Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@ApiParam(name = "Corpo", value = "Representação de uma nova cidade")
								 @RequestBody @Valid CidadeInput cidadeInput){
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
		}  catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Atualiza uma cidade por Id")
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", example = "1")
			                     @PathVariable Long cidadeId,
								 @ApiParam(name = "Corpo", value = "Representação de uma cidade com novos dados")
								 @RequestBody @Valid CidadeInput cidadeInput){

		try{
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Deleta uma cidade por Id")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId){
		cadastroCidade.excluir(cidadeId);
	}

}
