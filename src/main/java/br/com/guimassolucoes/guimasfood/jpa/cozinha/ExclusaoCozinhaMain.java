package br.com.guimassolucoes.guimasfood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.guimassolucoes.guimasfood.GuimasfoodApiApplication;
import br.com.guimassolucoes.guimasfood.domain.service.CozinhaService;

public class ExclusaoCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				GuimasfoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaService cozinhaService = configurableApplicationContext.getBean(CozinhaService.class);

		cozinhaService.remover(1L);
	}

}
