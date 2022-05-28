package com.cfrdocarmo.cfrfood.api.model;

import com.cfrdocarmo.cfrfood.domain.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;

    @ApiModelProperty("89.90")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "12.90")
    private BigDecimal taxaFrete;

    @ApiModelProperty(example = "101.10")
    private BigDecimal valorTotal;

    @ApiModelProperty("CRIADO")
    private StatusPedido status;

    @ApiModelProperty("2022-05-27T13:57:15Z")
    private OffsetDateTime dataCriacao;

    @ApiModelProperty("2022-05-27T13:58:15Z")
    private OffsetDateTime dataConfirmacao;

    @ApiModelProperty("2022-05-27T14:57:15Z")
    private OffsetDateTime dataEntrega;

    @ApiModelProperty("2022-05-27T13:57:17Z")
    private OffsetDateTime dataCancelamento;

    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoEntrega;
    private List<ItemPedidoModel> itens = new ArrayList<>();

}
