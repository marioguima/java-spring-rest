package br.com.guimassolucoes.guimasfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;
import br.com.guimassolucoes.guimasfood.domain.repository.CozinhaRepository;
import br.com.guimassolucoes.guimasfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	/**
	 * Para passar via get com um nome de variável basta utilizar
	 * o @RequestParam("nomeParametro")
	 * 
	 * Se não espeficiar um nome ele vai usar o nome da variável
	 * 
	 * Dessa forma para chamar a URI fica assim
	 * 
	 * /teste/cozinhas/por-nome/nome=Tailandeza
	 * 
	 * Ao utilizar Strping Data não precisa mais utilizar @RquestParam
	 * 
	 * Por padrão ele passa o nome da variável como parâmetro na chamada
	 * 
	 * @param nome
	 * @return
	 */
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findTodasByNomeCozinhaContaining(nome);
	}

	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findByNomeCozinha(nome);
	}

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findTodosByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/por-nome-e-cozinha-id")
	public List<Restaurante> consultarPorNomeAndCozinhaId(String nome, Long id) {
		return restauranteRepository.consultarPorNomeAndCozinhaId(nome, id);
	}

	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> findFirstByNmRestauranteContaining(String nome) {
		return restauranteRepository.findFirstByNmRestauranteContaining(nome);
	}

	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> findTop2ByNmRestauranteContaining(String nome) {
		return restauranteRepository.findTop2ByNmRestauranteContaining(nome);
	}

	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/quantidade-por-cozinha")
	public int countByCozinhaId(Long id) {
		return restauranteRepository.countByCozinhaId(id);
	}

}
