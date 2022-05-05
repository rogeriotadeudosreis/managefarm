package br.com.rogerio.manageFarm.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorFormDto {

    private String titulo;
    private String campo;
    private String erro;

}
