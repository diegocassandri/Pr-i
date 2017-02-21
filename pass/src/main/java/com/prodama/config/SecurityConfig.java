package com.prodama.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Value("${conexao.usuario}")
	 private static String user;
	 
	 @Value("${conexao.senha}")
	 private static String pass;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("prodama").password("prodama").roles("ADMIN");
		auth.ldapAuthentication().contextSource().url("ldap://192.168.7.48:389/dc=prodama,dc=com,dc=br")
				.managerDn("uid=admin,ou=system")
				.managerPassword("secret")
				.and().
				userSearchBase("ou=users")
				.userSearchFilter("(cn={0})");
		auth.userDetailsService(userDetailsService())/*.passwordEncoder(passwordEncoder())*/;
		
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * /*auth.userDetailsService(userDetailsService).passwordEncoder(
	 * passwordEncoder()) auth.inMemoryAuthentication()
	 * .withUser("prodama").password("prodama").roles("ADMIN");
	 * 
	 * 
	 * }
	 * 
	 * auth .ldapAuthentication() .userDnPatterns("uid={0},ou=people")
	 * .groupSearchBase("ou=groups") .contextSource(contextSource())
	 * .passwordCompare() .passwordEncoder(new LdapShaPasswordEncoder())
	 * .passwordAttribute("userPassword");
	 */

	/*
	 * @Bean public DefaultSpringSecurityContextSource contextSource() { return
	 * new DefaultSpringSecurityContextSource(Arrays.asList(
	 * "ldap://192.168.7.48:389/"), "dc=PRODAMA,dc=COM,dc=BR"); }
	 */

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**").antMatchers("/images/**").antMatchers("/stylesheets/**")
				.antMatchers("/resources/**").antMatchers("/js/**").antMatchers("/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/**")
				.hasRole("ADMIN").and().csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().exceptionHandling()
				.accessDeniedPage("/error/403").and().sessionManagement().invalidSessionUrl("/login");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}