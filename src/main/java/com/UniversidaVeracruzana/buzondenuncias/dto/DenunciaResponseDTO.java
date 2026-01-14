package com.UniversidaVeracruzana.buzondenuncias.dto;

import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoDenuncia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaResponseDTO {
    private Integer denunciaId;
    private String nombreDenunciante; // puede ser null
    private String nombreAcusado;
    private String apellidoPAcusado;
    private String apellidoMAcusado;
    private String descripcion;
    private String lugarHechos;
    private String fechaHechos;
    private String relacion;
    private String entidadAcademicaNombre;
    private EstadoDenuncia estado;
    private String procedimientoNombre;

}
