package com.bib404.system_bib404.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bib404.system_bib404.service.impl.UserService;


@Configuration 
@EnableWebSecurity
public class SegurityConf extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("userService")
	private UserService userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/*","/js/*","/webfonts/*","/usuarios/registrarse","/usuarios/listUser","/usuarios/addUser","/usuarios/registrarse?error").permitAll()
			.anyRequest().permitAll()//authenticated()
			.and()
			.formLogin().loginPage("/").loginProcessingUrl("/logincheck")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/index_usuario").permitAll()
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/index").permitAll();		
		super.configure(http);
	}

	
}
