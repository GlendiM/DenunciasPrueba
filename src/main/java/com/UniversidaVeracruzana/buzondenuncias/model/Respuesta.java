package com.UniversidaVeracruzana.buzondenuncias.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Respuesta {
    private int codigo;
    private String titulo;
    private String mensaje;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

    public Respuesta(){}

}
