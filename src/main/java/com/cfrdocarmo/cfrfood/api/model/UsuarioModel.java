package com.cfrdocarmo.cfrfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty("Carlos Felipe")
    private String nome;

    @ApiModelProperty("cfrdocarmo@gmail.com")
    private String email;

}
