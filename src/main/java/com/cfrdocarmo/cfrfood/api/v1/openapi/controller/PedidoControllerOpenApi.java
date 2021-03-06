package com.cfrdocarmo.cfrfood.api.v1.openapi.controller;

import com.cfrdocarmo.cfrfood.api.exceptionHandler.Problem;
import com.cfrdocarmo.cfrfood.api.v1.model.PedidoModel;
import com.cfrdocarmo.cfrfood.api.v1.model.PedidoResumoModel;
import com.cfrdocarmo.cfrfood.api.v1.model.input.PedidoInput;
import com.cfrdocarmo.cfrfood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar a resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa os pedidos")
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar a resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por código")
    @ApiResponse(responseCode = "400", description = "Código pedido inválido", content = @Content(schema = @Schema( implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Pedido não encontrada", content = @Content(schema = @Schema( implementation = Problem.class)))
    public PedidoModel buscar(@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);

    @ApiOperation("Registra um pedido")
    @ApiResponse(responseCode = "201", description = "Pedido registrado")
    public PedidoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

}