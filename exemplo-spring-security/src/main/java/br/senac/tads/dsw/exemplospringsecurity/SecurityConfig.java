/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplospringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author fernando.tsuda
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static PasswordEncoder plainPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence cs) {
                return cs.toString();
            }

            @Override
            public boolean matches(CharSequence cs, String hashSenha) {
                return hashSenha != null && hashSenha.equals(cs.toString());
            }
        };
    }
    
    public static PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bcryptPasswordEncoder();
    }
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Correção para acessar console do banco H2 quando estiver com Security
    	// Resolve erro relacionado ao x-frame-options
    	http.headers().frameOptions().sameOrigin();
    	
    	http.csrf().disable()
    		.authorizeRequests()
    			.antMatchers("/css/**", "/js/**", "/img/**", "/font/**", "/", "/index.html", "/h2/**").permitAll()
    			.antMatchers("/protegido/peao/**").hasRole("PEAO") // ou hasAuthority("ROLE_PEAO")
    			.antMatchers("/protegido/fodon/**").hasRole("FODON") // ou hasAuthority("ROLE_FODON")
    			.antMatchers("/protegido/god/**").hasRole("GOD") // ou hasAuthority("ROLE_GOD")
    			.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/custom-login")
					.usernameParameter("username")
					.passwordParameter("senha")
					.defaultSuccessUrl("/home")
					.permitAll()
			.and()
				.logout()
					.logoutSuccessUrl("/custom-login?logout")
					.invalidateHttpSession(true)
			.and()
				.exceptionHandling()
					.accessDeniedPage("/erro/403");
    	
    }
    
}