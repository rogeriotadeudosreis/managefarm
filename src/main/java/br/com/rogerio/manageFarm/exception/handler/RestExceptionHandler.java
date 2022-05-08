package br.com.rogerio.manageFarm.exception.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
}
