package com.UniversidaVeracruzana.buzondenuncias.mapper;

import org.springframework.stereotype.Component;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.model.NomCompleto;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;

@Component
public class UsuariosMapper {
    public Usuarios toEntity(UsuarioRequestDTO dto){
        if(dto == null){
            return null;
        }
        NomCompleto nombreCompleto = NomCompleto.builder()
            .nombre(dto.getNombre())
            .apellidoP(dto.getApellidoP())
            .apellidoM(dto.getApellidoM())
            .build();

        return Usuarios.builder()
            .tipoUser(dto.getTipoUser())
            .matricula(dto.getMatricula())
            .numeroPersonal(dto.getNumeroPersonal())
            .nombre(nombreCompleto)
            .correoElectronico(dto.getCorreoElectronico())
            .password(dto.getPassword()) // encriptada
            .edad(dto.getEdad())
            .sexo(dto.getSexo())
            .identidadSexogenerica(dto.getIdentidadSexogenerica())
            .dependenciaAdscripcion(dto.getDependenciaAdscripcion())
            .contacto(dto.getContacto())
            .rol(dto.getRol())
            .status(dto.getStatus())
            .build();
    }
    public UsuarioResponseDTO toDto(Usuarios usuario){
        if (usuario == null) {
            return null;
        }
        String nombreCompleto = usuario.getNombre().getNombre() + " " +
                                usuario.getNombre().getApellidoP() + " " +
                                usuario.getNombre().getApellidoM();
        
        return UsuarioResponseDTO.builder()
                .usuarioId(usuario.getUsuarioId())
                .tipoUser(usuario.getTipoUser())
                .matricula(usuario.getMatricula())
                .numeroPersonal(usuario.getNumeroPersonal())
                .nombreCompleto(nombreCompleto)
                .correroElectronico(usuario.getCorreoElectronico())
                .edad(usuario.getEdad())
                .sexo(usuario.getSexo())
                .identidadSexogenerica(usuario.getIdentidadSexogenerica())
                .regionNombre(usuario.getRegion() != null ? usuario.getRegion().getRegion().name() : null)
                .entidadAcademica(usuario.getEntidadAcademica() != null ? usuario.getEntidadAcademica().getNombre() : null)
                .dependenciaAdscripcion(usuario.getDependenciaAdscripcion())
                .contacto(usuario.getContacto())
                .rol(usuario.getRol())
                .status(usuario.getStatus())
                .build();

    }

}
