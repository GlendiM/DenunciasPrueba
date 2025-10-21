package com.UniversidaVeracruzana.buzondenuncias.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "nombreCompleto",
            expression = "java(usuario.getNombre().getNombre() + \" \" + usuario.getNombre().getApellidoP() + \" \" + usuario.getNombre().getApellidoM())")
    @Mapping(target = "regionNombre", source = "region.region")
    @Mapping(target = "entidadAcademica", source = "entidadAcademica.nombre")
    UsuarioResponseDTO toDto(Usuarios usuario);
    
    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "nombre.nombre", source = "nombre")
    @Mapping(target = "nombre.apellidoP", source = "apellidoP")
    @Mapping(target = "nombre.apellidoM", source = "apellidoM")
    @Mapping(target = "region", ignore = true)  // Se asigna en el servicio
    @Mapping(target = "entidadAcademica", ignore = true)  // Se asigna en el servicio
    @Mapping(target = "password", source = "password")
    Usuarios toEntity(UsuarioRequestDTO dto);

}
