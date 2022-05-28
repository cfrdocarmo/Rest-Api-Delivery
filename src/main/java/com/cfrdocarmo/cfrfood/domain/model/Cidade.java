package com.cfrdocarmo.cfrfood.domain.model;

import com.cfrdocarmo.cfrfood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

	@ApiModelProperty(example = "1")
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(example = "Uberlândia")
	@NotBlank
	@Column(nullable = false)
	private String nome;

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	@Valid
	@ManyToOne
	@NotNull()
	@ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
	@JoinColumn(nullable = false)
	private Estado estado;
}
