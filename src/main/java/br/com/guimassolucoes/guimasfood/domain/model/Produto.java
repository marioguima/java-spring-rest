package br.com.guimassolucoes.guimasfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "tab_produtos")
public class Produto {

	@EqualsAndHashCode.Include
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private Boolean ativo;

	/**
	 * Utilizando o @JoinColumn(name = "nome_da-coluna") podemos especificar qual o
	 * nome da coluna que terá esse relacionamento
	 */
//	@JoinColumn(name = "restaurante")
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

}
