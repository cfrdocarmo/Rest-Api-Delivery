package com.cfrdocarmo.cfrfood.domain.model;

import com.cfrdocarmo.cfrfood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@Valid
	@ManyToOne
	@NotBlank
	@ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
	@JoinColumn(nullable = false)
	private Estado estado;
}
