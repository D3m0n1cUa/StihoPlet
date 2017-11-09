package org.dichcorp.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DBConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public HibernateTemplate hibernateTemplate() {
	return new HibernateTemplate(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
	LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
	localSessionFactoryBean.setDataSource(getDataSource());
	localSessionFactoryBean.setPackagesToScan("org.dichcorp.model");
	localSessionFactoryBean.setHibernateProperties(hibernateProperties());

	try {
	    localSessionFactoryBean.afterPropertiesSet();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return localSessionFactoryBean.getObject();
    }

    @Bean
    public DataSource getDataSource() {
	BasicDataSource basicDataSource = new BasicDataSource();
	basicDataSource.setDriverClassName(environment.getProperty("database.driverClassName"));
	basicDataSource.setUrl(environment.getProperty("database.url"));
	basicDataSource.setUsername(environment.getProperty("database.username"));
	basicDataSource.setPassword(environment.getProperty("database.password"));

	return basicDataSource;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
	return new HibernateTransactionManager(sessionFactory());
    }

    private Properties hibernateProperties() {
	Properties properties = new Properties();
	properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
	properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
	properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));

	return properties;
    }
}
