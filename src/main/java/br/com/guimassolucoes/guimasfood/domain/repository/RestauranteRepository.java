package br.com.guimassolucoes.guimasfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	@Query("from Restaurante r join r.cozinha")
	List<Restaurante> findAll();
	
	/**
	 * no prefixo podemos usar find, query, read, get, stream
	 * 
	 * não muda nada, é apenas uma questão de escolha
	 * 
	 * Além do prefixo podemos usar algumas palavras/comandos para o spring
	 * 
	 * First, Count, Top[x] ...
	 * 
	 * @param taxaInicial
	 * @param taxaFinal
	 * @return
	 */
	List<Restaurante> findTodosByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	@Query("from Restaurante where nmRestaurante like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNomeAndCozinhaId(String nome, @Param("id") Long cozinha);

//	List<Restaurante> findByNmRestauranteContainingAndCozinhaId(String nome, Long cozinha);

	Optional<Restaurante> findFirstByNmRestauranteContaining(String nome);

	List<Restaurante> findTop2ByNmRestauranteContaining(String nome);

	int countByCozinhaId(Long id);

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
