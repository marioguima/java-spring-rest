package br.com.guimassolucoes.guimasfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.guimassolucoes.guimasfood.domain.model.Estado;

@ResponseBody
public interface EstadoRepository extends JpaRepository<Estado, Long> {	

}
