package com.jazzchris.musicchallenge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/crud/*/delete").hasRole("ADMIN")
		.antMatchers("/crud/*/showForm*").hasRole("MANAGER")
		.antMatchers("/crud/**").hasRole("USER")
		.and()
		.formLogin()
		.permitAll()
		.and()
		.logout().permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserDetails admin = User.withUsername("admin")
				.password("{bcrypt}$2a$04$MNrZOBJzMk3b3GS.LoUMxeA477HhfRSTg5NyJztyikS1Lx1e8c25K")
				.authorities("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN")
				.build();
		
		UserDetails manager = User.withUsername("manager")
				.password("{bcrypt}$2a$04$FV0DuEngXXk1mbFvkTuxI.DIFOjVisiQwuoJPLiqxygRJPcJtw36a")
				.authorities("ROLE_USER", "ROLE_MANAGER")
				.build();
		
		UserDetails user = User.withUsername("user")
				.password("{bcrypt}$2a$04$d0jdw7ANgN8cpzJX4/GjOOzosKvZMo94BHO0TvpTINIZAn4K6DWSO")
				.authorities("ROLE_USER")
				.build();
		
		auth.inMemoryAuthentication()
			.withUser(admin)
			.withUser(manager)
			.withUser(user);
	}
}
