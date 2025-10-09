package com.UniversidaVeracruzana.buzondenuncias.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoDenuncia {
    RECIBIDA("Recibida"),
    EN_REVISION("En revisión"),
    REQ_INFORMACION("Requiere más información"),
    CANALIZADA("Canalizada"),
    RESUELTA("Resuelta"),
    ARCHIVADA("Archivada");

    private final String valorEnDB;

    public static EstadoDenuncia fromDbValue(String valor){
        if(valor == null){
            return null;
        }

        return Stream.of(EstadoDenuncia.values())
            .filter(c -> c.getValorEnDB().equals(valor))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Estado de Denuncia no Válido: " + valor));
        
    }

}
