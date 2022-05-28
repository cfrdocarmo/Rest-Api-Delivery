package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.GrupoModel;
import com.cfrdocarmo.cfrfood.api.model.input.GrupoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiParam(value = "Lista todos os Grupos")
    public List<GrupoModel> listar();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public GrupoModel buscarPorId(@ApiParam(name = "GrupoID", value = "ID de um Grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Cadastrar um Grupo")
    @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    public GrupoModel adicionar(@ApiParam(name = "Corpo", value = "Representação de um novo Grupo", required = true) GrupoInput grupoInput);

    @ApiOperation("Atualizar um Grupo por ID")
    @ApiResponse(responseCode = "200", description = "Grupo atualizado")
    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public GrupoModel atualizar(@ApiParam(value = "ID de um grupo", example = "1", required = true)
                                Long grupoId,
                                @ApiParam(name = "Corpo", value = "Representação de um grupo com novos dados")
                                GrupoInput grupoInput);

    @ApiOperation("Deleta um grupo por ID")
    @ApiResponse(responseCode = "204", description = "Grupo excluído")
    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

}
