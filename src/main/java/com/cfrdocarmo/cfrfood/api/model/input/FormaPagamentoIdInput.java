package com.cfrdocarmo.cfrfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdInput {

    @NotNull
    private Long id;
}
