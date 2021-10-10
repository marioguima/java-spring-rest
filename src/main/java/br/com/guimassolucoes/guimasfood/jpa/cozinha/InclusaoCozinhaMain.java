package br.com.guimassolucoes.guimasfood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.guimassolucoes.guimasfood.GuimasfoodApiApplication;
import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				GuimasfoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = configurableApplicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha1=new Cozinha();
		cozinha1.setNomeCozinha("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNomeCozinha("Japonesa");
		
		cozinhaRepository.salvar(cozinha1);
		cozinhaRepository.salvar(cozinha2);
	}

}
