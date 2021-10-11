package br.com.guimassolucoes.guimasfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.guimassolucoes.guimasfood.domain.model.Estado;
import br.com.guimassolucoes.guimasfood.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Estado> todos() {
		List<Estado> result = em.createQuery("from Estado", Estado.class).getResultList();

		return result;
	}

	@Override
	public Estado porId(Long id) {
		return em.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return em.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Long estadoId) {
		Estado estado = porId(estadoId);

		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		em.remove(estado);
	}

}
