package com.cfrdocarmo.cfrfood.api.v2.model;

import com.cfrdocarmo.cfrfood.api.v1.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
@ApiModel("CozinhaModel")
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2> {

    @ApiModelProperty(example = "1")
    private Long idCozinha;

    @ApiModelProperty(example = "Brasileira")
    private String nomeCozinha;

}
