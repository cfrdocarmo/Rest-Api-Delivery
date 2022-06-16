package com.cfrdocarmo.cfrfood.api.v2.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v2.model.CozinhaModelV2;
import com.cfrdocarmo.cfrfood.api.v2.model.input.CozinhaInputV2;
import com.cfrdocarmo.cfrfood.domain.model.Cozinha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {

    @ApiOperation("Lista as cozinhas com paginação")
    public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 10) Pageable pageable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CozinhaModelV2 buscarPorId(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
    public CozinhaModelV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInputV2 cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponse(responseCode = "200", description = "Cozinha atualizada")
    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CozinhaModelV2 atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
                                    @ApiParam(name = "corpo", value = "Representação de uma nova cozinha com novos dados", required = true) CozinhaInputV2 cozinhaInput);

    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponse(responseCode = "204", description = "Cozinha excluída")
    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);


}
