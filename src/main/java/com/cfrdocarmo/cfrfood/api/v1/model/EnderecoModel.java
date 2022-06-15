package com.cfrdocarmo.cfrfood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @ApiModelProperty(example = "60000-000")
    private String cep;

    @ApiModelProperty(example = "Rua Fulano de Tal")
    private String logradouro;

    @ApiModelProperty(example = "123")
    private String numero;

    @ApiModelProperty(example = "Próximo ao Hospita")
    private String complemento;

    @ApiModelProperty(example = "Jardim Paulista")
    private String bairro;

    private CidadeResumoModel cidade;

}
