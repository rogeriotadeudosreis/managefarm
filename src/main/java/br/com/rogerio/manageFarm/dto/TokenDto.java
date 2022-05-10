package br.com.rogerio.manageFarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {


    private final String token;
    private final String tipo;

    public TokenDto(String token, String tipo) {

        this.token = token;
        this.tipo = tipo;
    }
}
