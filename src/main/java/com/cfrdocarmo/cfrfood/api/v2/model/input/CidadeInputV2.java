package com.cfrdocarmo.cfrfood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("CidadeInput")
public class CidadeInputV2 {


    @NotBlank
    @ApiModelProperty(example = "Uberlândia", required = true)
    private String nomeCidade;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long idEstado;

}
