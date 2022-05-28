package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.CidadeModel;
import com.cfrdocarmo.cfrfood.api.model.FormaPagamentoModel;
import com.cfrdocarmo.cfrfood.api.model.input.CidadeInput;
import com.cfrdocarmo.cfrfood.domain.model.Restaurante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestaurantesFormasPagamentoControllerOpenApi {

    @ApiOperation("Lista as Formas de Pagamento associadas a restaurante")
    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public List<FormaPagamentoModel> listar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Desassociação de um restaurante com Forma de Pagamento")
    @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante ou forma de Pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void desassociar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                            @ApiParam(name = "FormaPagamentoID", value = "ID de uma Forma de Pagamento", example = "1", required = true) Long formaPagamentoId);

    @ApiOperation("Associação de um restaurante com Forma de Pagamento")
    @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Restaurante ou forma de Pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void associar(@ApiParam(name = "RestauranteID", value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                         @ApiParam(name = "FormaPagamentoID", value = "ID de uma Forma de Pagamento", example = "1", required = true) Long formaPagamentoId);

}