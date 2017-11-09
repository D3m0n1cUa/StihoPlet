package org.dichcorp.configuration;

import org.dichcorp.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/", "/index", "/registration", "/login").permitAll().antMatchers("/**")
		.hasAnyAuthority("ROLE_USER", "ROLE_ADMIN").and().formLogin() //login configuration
		.loginPage("/login").loginProcessingUrl("/appLogin").usernameParameter("login")
		.passwordParameter("password").defaultSuccessUrl("/user").failureUrl("/login?error").and().logout() //logout configuration
		.logoutUrl("/appLogout").logoutSuccessUrl("/login?logout").and().rememberMe()
		.rememberMeParameter("remember-me").tokenRepository(tokenRepository).tokenValiditySeconds(86400).and()
		.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	super.configure(auth);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
	PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
		"remember-me", userDetailsService, tokenRepository);
	return tokenBasedservice;
    }

}
