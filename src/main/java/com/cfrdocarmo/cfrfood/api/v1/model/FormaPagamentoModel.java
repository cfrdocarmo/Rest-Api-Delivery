package com.cfrdocarmo.cfrfood.api.v1.model;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

    @ApiParam(example = "1")
    private Long id;

    @ApiParam(example = "Cartão de Crédito")
    private String descricao;
}
