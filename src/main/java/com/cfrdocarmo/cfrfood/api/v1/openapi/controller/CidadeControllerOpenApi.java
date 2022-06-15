package com.cfrdocarmo.cfrfood.api.v1.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.CidadeModel;
import com.cfrdocarmo.cfrfood.api.v1.model.input.CidadeInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation(value = "Lista todas as Cidades")
    public CollectionModel<CidadeModel> listar();

    @ApiOperation("Busca uma cidade por Id")
    @ApiResponse(responseCode = "400",
            description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CidadeModel buscarPorId(@ApiParam(name = "CidadeID", value = "ID de uma Cidade", example = "1", required = true)
                                   Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    public CidadeModel adicionar(@ApiParam(name = "Corpo", value = "Representação de uma nova cidade", required = true)
                                 CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por Id")
    @ApiResponse(responseCode = "200", description = "Cidade atualizada")
    @ApiResponse(responseCode = "404",
            description = "Cidade não encontrada", content = @Content(schema = @Schema( implementation = Problem.class)))
    public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", example = "1", required = true)
                                 Long cidadeId,
                                 @ApiParam(name = "Corpo", value = "Representação de uma cidade com novos dados")
                                 CidadeInput cidadeInput);

    @ApiOperation("Deleta uma cidade por Id")
    @ApiResponse(responseCode = "204", description = "Cidade excluída")
    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema( implementation = Problem.class)))
    public void remover(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}