package br.com.guimassolucoes.guimasfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeEmUsoException;
import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping
	private List<Cozinha> todas() {
		return cozinhaService.todas();
	}

	@GetMapping("/{cozinhaId}")
	private ResponseEntity<Cozinha> porId(@PathVariable Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaService.porId(cozinhaId);

		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaWorkBase = cozinhaService.porId(cozinhaId);

		if (cozinhaWorkBase.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaWorkBase.get(), "id");

			Cozinha cozinhaSalva = salvar(cozinhaWorkBase.get());
			return ResponseEntity.ok(cozinhaSalva);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaid}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaid) {
		try {
			cozinhaService.remover(cozinhaid);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
