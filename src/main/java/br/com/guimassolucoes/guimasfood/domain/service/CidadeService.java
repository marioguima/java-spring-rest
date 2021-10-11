package br.com.guimassolucoes.guimasfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeEmUsoException;
import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.guimassolucoes.guimasfood.domain.model.Cidade;
import br.com.guimassolucoes.guimasfood.domain.model.Estado;
import br.com.guimassolucoes.guimasfood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	EstadoService estadoService;

	public List<Cidade> todos() {
		return cidadeRepository.findAll();
	}

	public Optional<Cidade> porId(Long cidadeId) {
		return cidadeRepository.findById(cidadeId);
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoService.porId(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de estado com código %d", estadoId)));

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);

	}

	public void remover(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com o código %d", cidadeId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade do código %d não pode ser removida, porque está em uso", cidadeId));
		}
	}
}
