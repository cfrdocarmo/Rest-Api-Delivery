package com.cfrdocarmo.cfrfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GrupoIdInput {

    @NotNull
    private Long id;
}
