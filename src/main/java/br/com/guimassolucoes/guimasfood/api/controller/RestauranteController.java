package br.com.guimassolucoes.guimasfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.guimassolucoes.guimasfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;
import br.com.guimassolucoes.guimasfood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> todos() {
		return restauranteService.todos();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> porId(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.porId(restauranteId);

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteWorkBase = restauranteService.porId(restauranteId);

			if (restauranteWorkBase != null) {
				BeanUtils.copyProperties(restaurante, restauranteWorkBase, "id");

				restauranteService.salvar(restauranteWorkBase);
				return ResponseEntity.ok(restauranteWorkBase);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campoValor) {

		Restaurante restauranteWorkBase = restauranteService.porId(restauranteId);

		if (restauranteWorkBase == null) {
			return ResponseEntity.notFound().build();
		}

		mergeCampos(campoValor, restauranteWorkBase);

		return atualizar(restauranteId, restauranteWorkBase);
	}

	private void mergeCampos(Map<String, Object> campoValor, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(campoValor, Restaurante.class);

		campoValor.forEach((campo, valor) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, campo);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(campo + " = " + valor + " = " + novoValor);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

}
