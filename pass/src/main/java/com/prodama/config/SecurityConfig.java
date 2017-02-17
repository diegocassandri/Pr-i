package com.prodama.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;





@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DefaultSpringSecurityContextSource source;


	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())*/;
		auth.inMemoryAuthentication()
		.withUser("prodama").password("prodama").roles("ADMIN");
		 auth.ldapAuthentication().contextSource(source)
         .userSearchBase("dc=users,dc=ldap")
         .userDnPatterns("cn={0},dc=users")
         .groupSearchBase("ou=groups")
         ;   
	}
	

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**")
			.antMatchers("/stylesheets/**")
			.antMatchers("/resources/**")
			.antMatchers("/js/**")
			.antMatchers("/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http .httpBasic()
        .and()
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
        .and()
            .csrf()
                .disable()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/error/403")
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login");
		

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}