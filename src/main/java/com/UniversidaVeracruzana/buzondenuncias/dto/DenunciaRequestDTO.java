package com.UniversidaVeracruzana.buzondenuncias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaRequestDTO {
    private Integer denuncianteID; // puede ser null para denuncias anonimas
    private String nombreAcusado;
    private String apellidoPAcusado;
    private String apellidoMAcusado;
    private String descripcion;
    private String lugarHechos;
    private String fechaHechos;
    private String relacion;
    private Integer entidadAcademicaId;
    private Integer procesoId;

}
