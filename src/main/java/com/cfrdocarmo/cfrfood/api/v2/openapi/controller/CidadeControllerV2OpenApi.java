package com.cfrdocarmo.cfrfood.api.v2.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v2.model.CidadeModelV2;
import com.cfrdocarmo.cfrfood.api.v2.model.input.CidadeInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {

    @ApiOperation("Lista todas cidades")
    public CollectionModel<CidadeModelV2> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CidadeModelV2 buscarPorId(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    public CidadeModelV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) CidadeInputV2 cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponse(responseCode = "200", description = "Cidade atualizada")
    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CidadeModelV2 atualizar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId,
                                   @ApiParam(name = "corpo", value = "Representação de uma nova cidade com novos dados", required = true) CidadeInputV2 cidadeInput);

    @ApiOperation("Exclui uma cidade")
    @ApiResponse(responseCode = "204", description = "Cidade excluída")
    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);
    
}
