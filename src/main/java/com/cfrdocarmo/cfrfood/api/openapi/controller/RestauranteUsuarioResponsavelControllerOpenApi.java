package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.FormaPagamentoModel;
import com.cfrdocarmo.cfrfood.api.model.UsuarioModel;
import com.cfrdocarmo.cfrfood.api.model.input.FormaPagamentoInput;
import com.cfrdocarmo.cfrfood.domain.model.Restaurante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @ApiOperation("Lista os usuários responsáveis associados a restaurante")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public List<UsuarioModel> listar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Desassociação de restaurante com usuário responsável")
    @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void desassociar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                            @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Associação de restaurante com usuário responsável")
    @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void associar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                         @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);
}