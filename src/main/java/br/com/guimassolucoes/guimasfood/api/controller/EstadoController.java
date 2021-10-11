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
import br.com.guimassolucoes.guimasfood.domain.model.Estado;
import br.com.guimassolucoes.guimasfood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	private List<Estado> todos() {
		return estadoService.todos();
	}

	@GetMapping("/{estadoId}")
	private ResponseEntity<Estado> porId(@PathVariable Long estadoId) {
		Optional<Estado> estado = estadoService.porId(estadoId);

		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody Estado estado) {
		return estadoService.salvar(estado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Optional<Estado> estadoWorkBase = estadoService.porId(estadoId);

		if (estadoWorkBase.isPresent()) {
			BeanUtils.copyProperties(estado, estadoWorkBase.get(), "id");

			Estado estadoSalvo = estadoService.salvar(estadoWorkBase.get());
			return ResponseEntity.ok(estadoSalvo);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {
		try {
			estadoService.remover(estadoId);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
