package com.cfrdocarmo.cfrfood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "Dados inválidos", position = 10)
    private String type;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 15)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 25)
    private String userMessage;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @ApiModelProperty(position = 5, example = "2021-06-19T13:57:58.076Z")
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "Lista de objetos ou campos que geraram o erro.", position = 30)
    private List<Object> objects;

    @ApiModel("Objeto Problema")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "preço")
        private String nome;

        @ApiModelProperty(example = "O  preço é obrigatório.")
        private String userMessage;
    }

}
