package com.cfrdocarmo.cfrfood.api.v1.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.EstadoModel;
import com.cfrdocarmo.cfrfood.api.v1.model.input.EstadoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista os Estados")
    public CollectionModel<EstadoModel> listar();

    @ApiOperation("Busca um Estado por ID")
    @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public EstadoModel buscarPorId(@ApiParam(name = "EstadoID", value = "ID de um estado", example = "1", required = true) Long estadoId);

    @ApiOperation("Cadastra um Estado")
    @ApiResponse(responseCode = "201", description = "Estado cadastrado")
    public EstadoModel adicionar(@ApiParam(name = "Corpo", value = "Representação de um novo Estado", required = true) EstadoInput estadoInput);

    @ApiOperation("Atualiza um Estado")
    @ApiResponse(responseCode = "200", description = "Estado atualizado")
    @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public EstadoModel atualizar(@ApiParam(name = "EstadoID", value = "ID de um estado", example = "1", required = true) Long estadoId,
                                 @ApiParam(name = "Corpo", value = "Representação de um novo Estado", required = true) EstadoInput estadoInput);

    @ApiOperation("Deleta um Estado")
    @ApiResponse(responseCode = "204", description = "Estado excluído")
    @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(@ApiParam(name = "EstadoID", value = "ID de um estado", example = "1", required = true) Long estadoId);

}
