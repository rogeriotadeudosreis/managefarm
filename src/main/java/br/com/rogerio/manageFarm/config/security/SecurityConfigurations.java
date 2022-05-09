package br.com.rogerio.manageFarm.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    // Configurações de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    // Configurações de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/listar/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/consultar-por-email/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/consultar-por-id/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    // Configurações de recursos estáticos(js, css, imagens, etc...não é o caso aqui nesta api)
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
