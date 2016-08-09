package jwd.afca.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication().withUser("aa").password("aa").roles("ADMIN");
/*			.jdbcAuthentication()
				.dataSource(dataSource)
//				.withDefaultSchema()
//				.withUser("username").password("password").roles("role");
				.usersByUsernameQuery(
						"select username, password, enabled from tbl_users where username=?")
				.authoritiesByUsernameQuery(
						"select username, role from tbl_users where username=?");
*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable();
		http
			.authorizeRequests()
				.antMatchers("/", "/home", "/classifieds", "/categories", "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/user").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/classifieds/**").permitAll()
				.antMatchers("/users").hasRole("ADMIN")
				.anyRequest().authenticated()
		.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.csrf().csrfTokenRepository(csrfTokenRepository())
		.and()
			.logout();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/static/**"); 
    }
	
	private CsrfTokenRepository csrfTokenRepository() {
		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		  repository.setHeaderName("X-XSRF-TOKEN");
		  return repository;
		}
}
