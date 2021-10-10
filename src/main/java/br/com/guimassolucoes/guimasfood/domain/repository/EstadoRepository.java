package br.com.guimassolucoes.guimasfood.domain.repository;

import java.util.List;

import br.com.guimassolucoes.guimasfood.domain.model.Estado;

public interface EstadoRepository {
	
	public List<Estado> todos();

	public Estado porId(Long id);

	public Estado salvar(Estado estado);

	public void remover(Estado estado);

}
