package com.cfrdocarmo.cfrfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModel {

    private Long Id;
    private String nome;
    private EstadoModel estado;

}
