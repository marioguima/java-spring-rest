package br.com.guimassolucoes.guimasfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cozinha> todas() {
		TypedQuery<Cozinha> query = em.createQuery("from Cozinha", Cozinha.class);

		return query.getResultList();
	}

	@Override
	public List<Cozinha> consultarPorNome(String nome) {
		return em.createQuery("from Cozinha where nomeCozinha like :nome", Cozinha.class)
				.setParameter("nome", "%" + nome + "%").getResultList();
	}

	@Override
	public Cozinha porId(Long id) {
		return em.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return em.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Long cozinhaId) {
		Cozinha cozinha = porId(cozinhaId);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}

		em.remove(cozinha);
	}

}
