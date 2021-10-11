package br.com.guimassolucoes.guimasfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * @param nome
	 * @return
	 */
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam String nome) {
		return cozinhaService.consultarPorNome(nome);
	}

}
