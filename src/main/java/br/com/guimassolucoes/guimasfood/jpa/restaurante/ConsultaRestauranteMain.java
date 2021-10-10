package br.com.guimassolucoes.guimasfood.jpa.restaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.guimassolucoes.guimasfood.GuimasfoodApiApplication;
import br.com.guimassolucoes.guimasfood.domain.model.Restaurante;
import br.com.guimassolucoes.guimasfood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				GuimasfoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		RestauranteRepository restaurantes = configurableApplicationContext.getBean(RestauranteRepository.class);

		List<Restaurante> todosRestaurantes = restaurantes.todos();

		for (Restaurante restaurante : todosRestaurantes) {
			System.out.printf("%s - %f - %s\n", restaurante.getNmRestaurante(), restaurante.getTaxaFrete(),
					restaurante.getCozinha().getNomeCozinha());
		}
	}

}
