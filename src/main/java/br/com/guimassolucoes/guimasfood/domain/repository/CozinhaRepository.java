package br.com.guimassolucoes.guimasfood.domain.repository;

import java.util.List;

import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> todas();

	public Cozinha porId(Long id);

	public Cozinha salvar(Cozinha cozinha);

	public void remover(Long id);

}
