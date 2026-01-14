package com.UniversidaVeracruzana.buzondenuncias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    private int codigo;
    private String titulo;
    private String mensaje;
    private Object datos;

    public Respuesta(int codigo, String titulo, String mensaje){
        this.codigo = codigo;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

}
