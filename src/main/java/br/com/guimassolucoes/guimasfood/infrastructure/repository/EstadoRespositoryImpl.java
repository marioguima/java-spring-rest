package br.com.guimassolucoes.guimasfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.guimassolucoes.guimasfood.domain.model.Estado;
import br.com.guimassolucoes.guimasfood.domain.repository.EstadoRepository;

@Repository
public class EstadoRespositoryImpl implements EstadoRepository {

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

	@Override
	public Estado salvar(Estado estado) {
		return em.merge(estado);
	}

	@Override
	public void remover(Estado estado) {
		estado = porId(estado.getId());
		em.remove(estado);
	}

}
