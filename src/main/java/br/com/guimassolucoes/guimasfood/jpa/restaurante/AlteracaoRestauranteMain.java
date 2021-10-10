package br.com.guimassolucoes.guimasfood.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.guimassolucoes.guimasfood.GuimasfoodApiApplication;
import br.com.guimassolucoes.guimasfood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				GuimasfoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		RestauranteRepository restaurantes = configurableApplicationContext.getBean(RestauranteRepository.class);

//		Restaurante restaurante = new Restaurante();
//		restaurante.setId(1L);
//		restaurante.setNmRestaurante("Burger King");
//		restaurante.setTaxaFrete(new BigDecimal("7.50"));
//		Cozinha cozinha = new Cozinha(); 
//		restaurante.setCozinha(cozinha.);

//		restaurantes.salvar(restaurante);
	}

}
