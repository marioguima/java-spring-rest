package br.com.guimassolucoes.guimasfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.service.CozinhaService;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaService cozinhaService;

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
		return cozinhaService.cozinhasPorNome(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaService.cozinhaPorNome(nome);
	}

}
