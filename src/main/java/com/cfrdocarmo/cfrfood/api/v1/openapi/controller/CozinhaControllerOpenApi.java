package com.cfrdocarmo.cfrfood.api.v1.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.CozinhaModel;
import com.cfrdocarmo.cfrfood.api.v1.model.input.CozinhaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiParam("Lista as Cozinhas com Paginação")
    public PagedModel<CozinhaModel> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CozinhaModel buscarPorId(@ApiParam(name = "CozinhaID", value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
    public CozinhaModel adicionar(@ApiParam(name = "Corpo", value = "Representação de uma nova Cozinha", required = true) CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma Cozinha")
    @ApiResponse(responseCode = "200", description = "Cozinha atualizada")
    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CozinhaModel atualizar(@ApiParam(name = "CozinhaID", value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
                                  @ApiParam(name = "Corpo", value = "Representação de uma nova Cozinha") CozinhaInput cozinhaInput);

    @ApiOperation("Deleta uma Cozinha")
    @ApiResponse(responseCode = "204", description = "Cozinha excluída")
    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(@ApiParam(name = "CozinhaID", value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);


}
