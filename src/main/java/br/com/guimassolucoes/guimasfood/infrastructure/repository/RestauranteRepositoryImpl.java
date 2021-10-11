package br.com.guimassolucoes.guimasfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;
import br.com.guimassolucoes.guimasfood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Restaurante> todos() {
		TypedQuery<Restaurante> query = em.createQuery("from Restaurante", Restaurante.class);

		return query.getResultList();
	}

	@Override
	public Restaurante porId(Long id) {
		return em.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return em.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(Long restauranteId) {
		Restaurante restaurante = porId(restauranteId);

		if (restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}

		em.remove(restaurante);
	}

}
