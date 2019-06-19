package com.github.adminfaces.starter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by aLeXcBa1990 on 24/11/2018.
 * 
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${spring.queries.users-by-username-query}")
    private String usersByUsernameQuery;

    @Value("${spring.queries.authorities-by-username-query}")
    private String authoritiesByUsernameQuery;
    
    @Autowired
    private DataSource dataSource;
    
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			// rest Login
			http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic().and().csrf()
					.disable();
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(authProvider);
		
		auth
		.jdbcAuthentication()
		.usersByUsernameQuery(usersByUsernameQuery)
		.authoritiesByUsernameQuery(authoritiesByUsernameQuery)
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// form login
//		http.authorizeRequests().antMatchers("/", "register.xhtml", "/login.xhtml", "/javax.faces.resource/**").permitAll().anyRequest()
//				.fullyAuthenticated().and().formLogin().loginPage("/login.xhtml").defaultSuccessUrl("/index.xhtml")
//				.failureUrl("/login.xhtml?authfailed=true").permitAll().and().logout().logoutSuccessUrl("/login.xhtml")
//				.logoutUrl("/j_spring_security_logout").and().csrf().disable();
		
		http.authorizeRequests().antMatchers("/", "/login.xhtml", "/register.xhtml", "/javax.faces.resource/**").permitAll().anyRequest().fullyAuthenticated();
		http.formLogin().loginPage("/login.xhtml").defaultSuccessUrl("/").failureUrl("/login.xhtml?authfailed=true").permitAll();
		http.logout().logoutSuccessUrl("/login.xhtml").logoutUrl("/j_spring_security_logout");
		http.csrf().disable();

		// allow to use ressource links like pdf
		http.headers().frameOptions().sameOrigin();
	}

}
