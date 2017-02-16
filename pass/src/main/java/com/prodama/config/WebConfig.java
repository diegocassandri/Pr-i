package com.prodama.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container ->
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"),
							new ErrorPage(HttpStatus.FORBIDDEN, "/error/403")));
	}
	
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter(
			FormattingConversionService conversionService) {
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/dashborad");
	}
	


}