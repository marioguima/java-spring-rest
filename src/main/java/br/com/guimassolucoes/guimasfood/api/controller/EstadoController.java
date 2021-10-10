package br.com.guimassolucoes.guimasfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guimassolucoes.guimasfood.domain.model.Estado;
import br.com.guimassolucoes.guimasfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping(value = "/estados", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EstadoController {

	@Autowired
	EstadoRepository estadoRepository;

	@GetMapping
	private List<Estado> todos() {
		return estadoRepository.todos();
	}

	@GetMapping("/{id}")
	private Estado porId(@PathVariable Long id) {
		return estadoRepository.porId(id);
	}

}
