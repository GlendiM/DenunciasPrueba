package com.UniversidaVeracruzana.buzondenuncias.dto;

import com.UniversidaVeracruzana.buzondenuncias.enums.RolesEnum;
import com.UniversidaVeracruzana.buzondenuncias.enums.Status_User;
import com.UniversidaVeracruzana.buzondenuncias.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Integer usuarioId;
    private TipoUsuario tipoUser;
    private String matricula;
    private String numeroPersonal;
    private String nombreCompleto;
    private String correoElectronico;
    private Integer edad;
    private String sexo;
    private String identidadSexogenerica;
    private String regionNombre;
    private String entidadAcademica;
    private String dependenciaAdscripcion;
    private String contacto;
    private RolesEnum rol;
    private Status_User status;

}
