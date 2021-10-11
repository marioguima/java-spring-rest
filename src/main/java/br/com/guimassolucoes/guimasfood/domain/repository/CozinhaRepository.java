package br.com.guimassolucoes.guimasfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	/***
	 * O nome do método pode ser apenas o nome da propriedade da entity
	 * 
	 * ex.
	 * 
	 * nomeCozinha(String nome);
	 * 
	 * Pode ser usando o prefixo findBy
	 * 
	 * findByNomeCozinha(String nome);
	 * 
	 * E pode ter qualquer palavra entre find e by, pois será ignorada
	 * 
	 * findTodasByNomeCozinha(String nome);
	 * 
	 * @param nome
	 * @return
	 */
	public List<Cozinha> findTodasByNomeCozinha(String nome);
	
	public Optional<Cozinha> findByNomeCozinha(String nome);

}
