package com.cfrdocarmo.cfrfood.api.v1.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.PermissaoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões associadas a um grupo")
    @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    CollectionModel<PermissaoModel> listar(
            @ApiParam(value = "ID do grupo", example = "1", required = true)
                    Long grupoId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    ResponseEntity<Void> removerPermissao(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
                     @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    ResponseEntity<Void> adicionarPermissao(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
                  @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

}