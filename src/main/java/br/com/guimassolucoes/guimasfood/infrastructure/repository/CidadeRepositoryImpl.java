package br.com.guimassolucoes.guimasfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.guimassolucoes.guimasfood.domain.model.Cidade;
import br.com.guimassolucoes.guimasfood.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Cidade> todos() {
		List<Cidade> result = em.createQuery("from Cidade", Cidade.class).getResultList();

		return result;
	}

	@Override
	public Cidade porId(Long id) {
		return em.find(Cidade.class, id);
	}

	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return em.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Long cidadeId) {
		Cidade cidade = porId(cidadeId);

		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		em.remove(cidade);
	}

}
