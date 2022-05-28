package com.cfrdocarmo.cfrfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoModel {

    @ApiModelProperty(example = "1")
    private Long produtoId;

    @ApiModelProperty(example = "Camarão ao Alho e Óleo")
    private String produtoNome;

    @ApiModelProperty(example = "3")
    private Integer quantidade;

    @ApiModelProperty(example = "30.00")
    private BigDecimal precoUnitario;

    @ApiModelProperty(example = "90.00")
    private BigDecimal precoTotal;

    @ApiModelProperty("Sem Cebola")
    private String observacao;

}
