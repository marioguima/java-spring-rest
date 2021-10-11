package br.com.guimassolucoes.guimasfood.domain.repository;

import java.util.List;

import br.com.guimassolucoes.guimasfood.domain.model.Cidade;

public interface CidadeRepository {

	public List<Cidade> todos();

	public Cidade porId(Long cidadeId);

	public Cidade salvar(Cidade cidade);

	public void remover(Long cidadeId);

}
