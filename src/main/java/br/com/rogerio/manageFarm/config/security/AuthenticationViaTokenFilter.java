package br.com.rogerio.manageFarm.config.security;


import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository repository;

    public AuthenticationViaTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valid = tokenService.isTokenValid(token);
        System.out.println("token do cliente: " + token);
        System.out.println("Token isValid: " + valid);

        if (valid) {
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);

    }

    private void autenticarCliente(String token) {

        Long idUsuario = tokenService.getIdUsuario(token);
        User usuario = repository.getOne(idUsuario);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
