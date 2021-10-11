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
import br.com.guimassolucoes.guimasfood.domain.model.Cidade;
import br.com.guimassolucoes.guimasfood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public List<Cidade> todos() {
		return cidadeService.todos();
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> porId(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = cidadeService.porId(cidadeId);

		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
		try {
			cidade = cidadeService.salvar(cidade);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		try {
			Optional<Cidade> cidadeWorkBase = cidadeService.porId(cidadeId);

			if (cidadeWorkBase.isPresent()) {
				BeanUtils.copyProperties(cidade, cidadeWorkBase.get(), "id");

				Cidade cidadeSalva = cidadeService.salvar(cidadeWorkBase.get());
				return ResponseEntity.ok(cidadeSalva);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
		try {
			cidadeService.remover(cidadeId);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

}
