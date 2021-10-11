package br.com.guimassolucoes.guimasfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeEmUsoException;
import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;
import br.com.guimassolucoes.guimasfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CozinhaService cozinhaService;

	public List<Restaurante> todos() {
		return restauranteRepository.findAll();
	}

	public Optional<Restaurante> porId(Long id) {
		return restauranteRepository.findById(id);
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.porId(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

	public Restaurante alterar(Long restauranteId) {
		return null;
	}

	public void remover(Long id) {
		try {
			restauranteRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de restaurante com o código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de código %d não pode ser removida, porque está em uso", id));

		}
	}

}
