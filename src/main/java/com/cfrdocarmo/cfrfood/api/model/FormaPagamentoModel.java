package com.cfrdocarmo.cfrfood.api.model;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

    @ApiParam(example = "1")
    private Long id;

    @ApiParam(example = "Cartão de Crédito")
    private String descricao;
}
