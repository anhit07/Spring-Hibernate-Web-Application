package com.anh.spring.web.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired public void
	 * configureGlobalSecurity(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("bill").password("abc123")
	 * .roles("USER");
	 * auth.inMemoryAuthentication().withUser("admin").password("root123")
	 * .roles("ADMIN");
	 * auth.inMemoryAuthentication().withUser("dba").password("root123")
	 * .roles("ADMIN", "DBA"); }
	 */

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests().antMatchers("/admin/**")
		 * .access("hasRole('ROLE_ADMIN')").and().formLogin().csrf() .disable();
		 */

		/*http.authorizeRequests()
				//.antMatchers("/", "/home").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				//.antMatchers("/db/**")
				//.access("hasRole('ADMIN') and hasRole('DBA')").and()
				.and()
				.formLogin().loginPage("/login").usernameParameter("ssoId")
				.passwordParameter("password").and().exceptionHandling()
				.accessDeniedPage("/Access_Denied");*/

		/*
		 * http.authorizeRequests().antMatchers("/secure/**").authenticated()
		 * .and().formLogin().usernameParameter("username")
		 * .passwordParameter("password").and().logout().and().httpBasic()
		 * .and().csrf();
		 */
		
		/*  http.authorizeRequests().anyRequest().authenticated().and().formLogin(
		  ) .loginPage("/login");
		 */
	}
}
