package br.com.guimassolucoes.guimasfood.domain.repository;

import java.util.List;

import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	public List<Restaurante> todos();

	public Restaurante porId(Long id);

	public Restaurante salvar(Restaurante restaurante);

	public void remover(Restaurante restaurante);

}
