package br.com.guimassolucoes.guimasfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_restaurantes")
public class Restaurante {

	/**
	 * o IDENTITY passa a responsabilidade da reganção do id para o banco nesse caso
	 * o MySql
	 */
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nm_restaurante", nullable = false)
	private String nmRestaurante;

	/**
	 * Para valores que requerem soma utilizar sempre BigDecimal
	 * https://www.devmedia.com.br/java-bigdecimal-trabalhando-com-mais-precisao/30286
	 */
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	/**
	 * Muitos restaurantes possuem uma cozinha
	 * 
	 * Caso precise especificar o nome do campo do relacionamento
	 * 
	 * JoinColumn(name = "cozinha_id")
	 * 
	 * Esse é apenas um exemplo usando @JoinColumn Porque o nome da coluna será
	 * cozinha_id por padrão
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

}
