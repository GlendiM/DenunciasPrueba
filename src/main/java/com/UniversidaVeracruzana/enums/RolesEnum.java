package com.UniversidaVeracruzana.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolesEnum {
    ALUMNO("RL_ALU", "ALUMNO"),
    DOCENTE("RL_DOC", "DOCENTE"),
    CGE("RL_CGE", "COORDINADOR DE GÉNERO"),
    ADM("RL_ADM", "ADMINISTRADOR");

    private final String codigo;
    private final String descripcion;

    // metodo para encontrar enum
    public static RolesEnum fromCode(String codigo){
        if(codigo == null){
            return null;
        }
        return Stream.of(RolesEnum.values())
            .filter(c -> c.getCodigo().equals(codigo))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Código del Rol no válido: " + codigo));
    }
    

}
