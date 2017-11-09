package org.dichcorp.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
	registerCharacterEncodingFilter(servletContext);
	super.beforeSpringSecurityFilterChain(servletContext);
    }

    private void registerCharacterEncodingFilter(final ServletContext servletContext) {
	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	characterEncodingFilter.setEncoding("UTF-8");
	characterEncodingFilter.setForceEncoding(true);
	FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter",
		characterEncodingFilter);
	filter.addMappingForUrlPatterns(null, false, "/*");
    }

}
