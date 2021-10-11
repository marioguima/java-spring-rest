package br.com.guimassolucoes.guimasfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guimassolucoes.guimasfood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
