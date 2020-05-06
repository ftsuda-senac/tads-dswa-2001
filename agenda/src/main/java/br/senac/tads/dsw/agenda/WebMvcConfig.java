package br.senac.tads.dsw.agenda;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	/**
	 * Define uma URL para acessar o diretório contendo as imagens carregadas via upload<br>
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/acesso-upload/*")
		.addResourceLocations("file:///C:/senac/tads/uploads/");
		
	}
}
