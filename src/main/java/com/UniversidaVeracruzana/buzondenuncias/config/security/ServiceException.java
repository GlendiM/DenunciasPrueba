package com.UniversidaVeracruzana.buzondenuncias.config.security;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private final int codigo;
    private final String titulo;
    private final HttpStatus status;

    public ServiceException(String mensaje){
        super(mensaje);
        this.codigo = 0;
        this.titulo = "Error del servicio";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ServiceException(String titulo, String mensaje, HttpStatus status){
        super(mensaje);
        this.codigo = 0;
        this.titulo = titulo;
        this.status = status;
    }

    public ServiceException(int codigo, String titulo, String mensaje, HttpStatus status){
        super(mensaje);
        this.codigo = codigo;
        this.titulo = titulo;
        this.status = status;

    }


}
