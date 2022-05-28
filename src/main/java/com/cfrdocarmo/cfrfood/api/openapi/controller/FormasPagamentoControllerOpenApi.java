package com.cfrdocarmo.cfrfood.api.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.model.FormaPagamentoModel;
import com.cfrdocarmo.cfrfood.api.model.input.FormaPagamentoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Formas de Pagamento")
public interface FormasPagamentoControllerOpenApi {

    @ApiOperation(value = "Lista as Formas de Pagamento")
    public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por Id")
    @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public ResponseEntity<FormaPagamentoModel> buscarPorID(@ApiParam(name = "FormaPagamentoID", value = "ID de uma Forma de Pagamento", example = "1", required = true) Long formaPagamentoId, ServletWebRequest request);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponse(responseCode = "201", description = "Forma de Pagamento cadastrada")
    public FormaPagamentoModel adicionar(@ApiParam(name = "Corpo", value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Atualiza uma forma de pagamento por id")
    @ApiResponse(responseCode = "200", description = "Forma de Pagamento atualizada")
    @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public FormaPagamentoModel atualizar(@ApiParam(name = "FormaPagamentoID", value = "ID de uma Forma de Pagamento", example = "1", required = true)
                                                     Long formaPagamentoId,
                                         @ApiParam(name = "Corpo", value = "Representação de uma nova forma de pagamento")
                                                     FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Deleta uma forma de pagamento por Id")
    @ApiResponse(responseCode = "204", description = "Forma de Pagamento excluída")
    @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void excluir(@ApiParam(name = "FormaPagamentoID", value = "ID de uma Forma de Pagamento", example = "1") Long formaPagamentoId);

}