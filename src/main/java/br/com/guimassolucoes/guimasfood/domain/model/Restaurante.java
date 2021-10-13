package br.com.guimassolucoes.guimasfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

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
	 * Ao usar ToOne o spring jpa usa o método eager para trazer os dados
	 * 
	 * Isso faz com que, mesmo usando o JsonIgnore, ele vá buscar os dados no banco
	 * fazendo select
	 * 
	 * Quando se usa LAZY e quer serializar, ou seja, trazer junto a classe
	 * Restauante precisa ignorar o hibernateLazyInitializer na serialização
	 * utilizando JsonIgnoreProperties
	 * 
	 * Caso queiramos usar o método EAGER teremos o comportamento padrão do spring
	 * jpa que é de fazer um select para cada item do relacionamento 'ToOne'
	 * 
	 * Para mudar esse comportamento e forçar que seja feito um único select devemos
	 * sobrescrever o método findAll padrão, que é o método utilizado pelo framework
	 * E isso se faz na classe repository através de um jpql
	 * 
	 * @Query("from Restaurante r join r.cozinha")
	 * 
	 * Utilizamos o join porque o restaurante sempre terá uma cozinha, podemos
	 * observar essa informação olhando a tabela de restaurante no campo que guarda
	 * a referência para cozinha, nesse caso cozinha_id. Por isso a anotação
	 * JoinColumn tem a propriedade nullable = false, que informa para o jpa que
	 * essa coluna é obrigatória, ou seja, não nula
	 * 
	 */
//	@JsonIgnore
//	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

}
