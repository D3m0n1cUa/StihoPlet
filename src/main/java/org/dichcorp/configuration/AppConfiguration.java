package org.dichcorp.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.dichcorp.*")
public class AppConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
	ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
	thymeleafViewResolver.setTemplateEngine(templateEngine);
	thymeleafViewResolver.setCharacterEncoding("UTF-8");
	return thymeleafViewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	templateEngine.setTemplateResolver(templateResolver);
	Set<IDialect> additionalDialects = new HashSet<>();
	additionalDialects.add(new SpringSecurityDialect());
	templateEngine.setAdditionalDialects(additionalDialects);
	return templateEngine;
    }

    @Bean
    public TemplateResolver templateResolver() {
	TemplateResolver templateResolver = new ServletContextTemplateResolver();
	templateResolver.setPrefix("WEB-INF/templates/");
	templateResolver.setSuffix(".html");
	templateResolver.setTemplateMode("HTML5");
	templateResolver.setCharacterEncoding("UTF-8");
	return templateResolver;

    }

    @Bean
    public MessageSource messageSource() {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("messages");
	return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
