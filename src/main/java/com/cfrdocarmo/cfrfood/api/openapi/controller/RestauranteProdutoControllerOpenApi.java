package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.ProdutoModel;
import com.cfrdocarmo.cfrfood.api.model.input.ProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

    @ApiOperation("Lista os Produtos de um restaurante")
    @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public List<ProdutoModel> listar(@ApiParam(name = "RestauranteID", value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
                                     @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                                               example = "false", defaultValue = "false") Boolean incluirInativos);

    @ApiOperation("Busca um produto de um restaurante")
    @ApiResponse(responseCode = "400", description = "ID do restaurante ou do produto inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public ProdutoModel buscar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
                               @ApiParam(name = "ProdutoID", value = "Id de um produto", example = "1", required = true) Long produtoId);

    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponse(responseCode = "201", description = "Produto Cadastrado")
    @ApiResponse(responseCode = "404", description = "restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public ProdutoModel adicionar(@ApiParam(name = "Id de um restaurante", example = "1", required = true) Long restauranteId,
                                  @ApiParam(name = "Corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);

    @ApiOperation("Atualiza um produto de um restaurante")
    @ApiResponse(responseCode = "0", description = "Produto Atualizado")
    @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public ProdutoModel atualizar(@ApiParam(name = "Id de um restaurante", example = "1", required = true) Long restauranteId,
                                  @ApiParam(name = "ProdutoID", value = "Id de um produto", example = "1", required = true) Long produtoId ,
                                  @ApiParam(name = "Corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);

}