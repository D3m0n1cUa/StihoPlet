package org.dichcorp.configuration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[] { AppConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return null;
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }

    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
	final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(
		servletAppContext);
	dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	return dispatcherServlet;
    }

}
