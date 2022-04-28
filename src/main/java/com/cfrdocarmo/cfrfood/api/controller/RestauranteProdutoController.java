package com.cfrdocarmo.cfrfood.api.controller;

import com.cfrdocarmo.cfrfood.api.assembler.ProdutoInputDisassembler;
import com.cfrdocarmo.cfrfood.api.assembler.ProdutoModelAssemble;
import com.cfrdocarmo.cfrfood.api.model.ProdutoModel;
import com.cfrdocarmo.cfrfood.api.model.input.ProdutoInput;
import com.cfrdocarmo.cfrfood.domain.model.Produto;
import com.cfrdocarmo.cfrfood.domain.model.Restaurante;
import com.cfrdocarmo.cfrfood.domain.repository.ProdutoRepository;
import com.cfrdocarmo.cfrfood.domain.service.CadastroProdutoService;
import com.cfrdocarmo.cfrfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private ProdutoModelAssemble produtoModelAssemble;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return produtoModelAssemble.toCollectionModel(produtoRepository.findByRestaurante(restaurante));
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        return produtoModelAssemble.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);

        produto.setRestaurante(restaurante);

        produto = cadastroProduto.salvar(produto);

        return produtoModelAssemble.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId ,
                                  @RequestBody @Valid ProdutoInput produtoInput) {

        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = cadastroProduto.salvar(produtoAtual);

        return produtoModelAssemble.toModel(produtoAtual);
    }

}
