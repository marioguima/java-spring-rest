package br.com.guimassolucoes.guimasfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeEmUsoException;
import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.guimassolucoes.guimasfood.domain.model.Estado;
import br.com.guimassolucoes.guimasfood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;

	public List<Estado> todos() {
		return estadoRepository.findAll();
	}

	public Optional<Estado> porId(Long id) {
		return estadoRepository.findById(id);
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void remover(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com o código %d", estadoId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removida, porque está em uso", estadoId));
		}
	}

}
