package com.cfrdocarmo.cfrfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdInput {

    @NotNull
    private Long id;
}
