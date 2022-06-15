package com.cfrdocarmo.cfrfood.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("PageModel")
public class PageModelOpenApi<T> {

    @ApiModelProperty(example = "10", value = "Quantidade de registros por páginas")
    private Long size;

    @ApiModelProperty(example = "50", value = "Quantidade total de registros")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total de páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private Long number;

}
