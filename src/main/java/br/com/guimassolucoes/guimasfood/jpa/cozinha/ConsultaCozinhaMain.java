package br.com.guimassolucoes.guimasfood.jpa.cozinha;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.guimassolucoes.guimasfood.GuimasfoodApiApplication;
import br.com.guimassolucoes.guimasfood.domain.model.Cozinha;
import br.com.guimassolucoes.guimasfood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				GuimasfoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhas = configurableApplicationContext.getBean(CozinhaRepository.class);

		List<Cozinha> todasCozinhas = cozinhas.todas();

		for (Cozinha cozinha : todasCozinhas) {
			System.out.println(cozinha.getNomeCozinha());
		}
	}

}
