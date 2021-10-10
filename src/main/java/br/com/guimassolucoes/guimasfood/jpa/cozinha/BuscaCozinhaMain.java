package br.com.guimassolucoes.guimasfood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.guimassolucoes.guimasfood.GuimasfoodApiApplication;
import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				GuimasfoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = configurableApplicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha = cozinhaRepository.porId(1L);

		System.out.println(cozinha.getNomeCozinha());
	}

}
