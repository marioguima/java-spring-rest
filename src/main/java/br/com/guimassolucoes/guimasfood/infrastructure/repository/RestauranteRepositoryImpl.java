package br.com.guimassolucoes.guimasfood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	EntityManager em;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		String jpql = ""
				+ "from Restaurante "
				+ "where "
				+ "  nmRestaurante like concat('%', :nome, '%') "
				+ "and taxaFrete between :taxaInicial and :taxaFinal";

		return em.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaInicial", taxaFreteInicial)
				.setParameter("taxaFinal", taxaFreteFinal)
				.getResultList();
	}

}
