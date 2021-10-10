package br.com.guimassolucoes.guimasfood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_cozinhas")
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * A propriedade JsonProperty define o nome do nó no xml, apenas quando o Accept é do tipo application/xml
	 * 
	 * Para esconder, proteger, ignorar uma coluna basta usar a anotação JsonIgnore
	 * Ps. neste caso não pode ter a anotação JsonProperty, porque ele tem prioridade e fará a coluna ser exibida mesmo com JsonIgnore 
	 * 
	 */
	@JsonProperty(value = "nome")
	@Column(name = "nm_cozinha", nullable = false)
	private String nomeCozinha;

}
