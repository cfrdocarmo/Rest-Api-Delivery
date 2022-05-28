package com.cfrdocarmo.cfrfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput {

    @NotBlank
    @ApiModelProperty(example = "Minas Gerais")
    private String nome;
}
