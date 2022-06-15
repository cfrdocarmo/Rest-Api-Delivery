package com.cfrdocarmo.cfrfood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "item pedidos")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

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
