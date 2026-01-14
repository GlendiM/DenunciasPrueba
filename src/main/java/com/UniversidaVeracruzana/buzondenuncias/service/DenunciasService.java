package com.UniversidaVeracruzana.buzondenuncias.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.UniversidaVeracruzana.buzondenuncias.config.security.ServiceException;
import com.UniversidaVeracruzana.buzondenuncias.dto.DenunciaRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.DenunciaResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoDenuncia;
import com.UniversidaVeracruzana.buzondenuncias.model.Denuncias;
import com.UniversidaVeracruzana.buzondenuncias.model.NomCompleto;
import com.UniversidaVeracruzana.buzondenuncias.model.Procedimientos;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;
import com.UniversidaVeracruzana.buzondenuncias.repository.DenunciasRepository;
import com.UniversidaVeracruzana.buzondenuncias.repository.EntidadAcademicaRepository;
import com.UniversidaVeracruzana.buzondenuncias.repository.ProcedimientosRepository;
import com.UniversidaVeracruzana.buzondenuncias.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DenunciasService {
    private final DenunciasRepository denunciasRepository;
    private final UsuarioRepository usuarioRepository;
    private final EntidadAcademicaRepository entidadAcademicaRepository;
    private final ProcedimientosRepository procedimientosRepository;

@Transactional(readOnly = true)
public List<DenunciaResponseDTO> findAll() {
    try {
        List<Denuncias> denuncias = denunciasRepository.findAll();
        
        return denuncias.stream()
                .map(this::convertToDTO)
                .filter(Objects::nonNull) // Filtrar nulos por si hay errores en convertToDTO
                .collect(Collectors.toList());
                
    } catch (Exception e) {
        System.err.println("Error en DenunciasService.findAll(): " + e.getMessage());
        e.printStackTrace();
        return new ArrayList<>(); // Retornar lista vacía en lugar de null
    }
}

@Transactional(readOnly = true)
public Optional<DenunciaResponseDTO> findById(Integer id){
    return denunciasRepository.findById(id)
            .map(this::convertToDTO);
}

public DenunciaResponseDTO create(DenunciaRequestDTO denunciaRequestDTO){
    Denuncias denuncia = new Denuncias();

    // Mapear campos básicos
    NomCompleto nombreAcusado = NomCompleto.builder()
            .nombre(denunciaRequestDTO.getNombreAcusado())
            .apellidoP(denunciaRequestDTO.getApellidoPAcusado())
            .apellidoM(denunciaRequestDTO.getApellidoMAcusado())
            .build();
    denuncia.setNombreAcusado(nombreAcusado);

    denuncia.setDescripcion(denunciaRequestDTO.getDescripcion());
    denuncia.setLugarHechos(denunciaRequestDTO.getLugarHechos());
    denuncia.setRelacion(denunciaRequestDTO.getRelacion());
    denuncia.setEstado(EstadoDenuncia.RECIBIDA); // Estado porn defecto

    // Asignar denunciante (null para anónimas)

    if(denunciaRequestDTO.getDenuncianteID() != null){
        Usuarios denunciante = usuarioRepository.findById(denunciaRequestDTO.getDenuncianteID())
                .orElseThrow(()-> new ServiceException("Denunciante no encontrado", "El denunciante especificado no existe", HttpStatus.BAD_REQUEST));
                denuncia.setDenunciante(denunciante);
    }

    // Asignar entidad academica
    if(denunciaRequestDTO.getEntidadAcademicaId() != null){
        denuncia.setEntidadAcademica(entidadAcademicaRepository.findById(denunciaRequestDTO.getEntidadAcademicaId())
                .orElseThrow(()-> new ServiceException("Entidad Académica no encontrada", "La entidad academica especificada no existe", HttpStatus.BAD_REQUEST)));
    }

    // Asignar procedimiento
    if(denunciaRequestDTO.getProcesoId() != null){
        Procedimientos procedimiento = procedimientosRepository.findById(denunciaRequestDTO.getProcesoId())
                .orElseThrow(()-> new ServiceException("Procedimiento no Encontrado", "El procedimiento especificado no existe", HttpStatus.BAD_REQUEST));
        denuncia.setProceso(procedimiento);
    }

    // Guardar una sola vez al final
    Denuncias savedDenuncia = denunciasRepository.save(denuncia);
    return convertToDTO(savedDenuncia);
}

public DenunciaResponseDTO updateEstado(Integer id, EstadoDenuncia nuevoEstado){
    Denuncias denuncia = denunciasRepository.findById(id)
            .orElseThrow(()-> new ServiceException("Denuncia no encontrada", "No se encontró la denuncia con ID: " + id, HttpStatus.NOT_FOUND));
    denuncia.setEstado(nuevoEstado);
    Denuncias updatedDenuncia = denunciasRepository.save(denuncia);
    return convertToDTO(updatedDenuncia);
}

@Transactional(readOnly = true)
public List<DenunciaResponseDTO> findByDenunciante(Integer denuncianteId){
    Usuarios denunciante = usuarioRepository.findById(denuncianteId)
            .orElseThrow(()-> new ServiceException("Denunciante no encontrado", "El denunciante especificado no existe", HttpStatus.BAD_REQUEST));
    return denunciasRepository.findByDenunciante(denunciante)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}

@Transactional(readOnly = true)
public List<DenunciaResponseDTO> findByEstado(EstadoDenuncia estado){
    return denunciasRepository.findByEstado(estado)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}

@Transactional(readOnly = true)
public List<DenunciaResponseDTO> findByEntidadAcademica(Integer entidadId){
    return denunciasRepository.findByEntidadAcademica_EntidadId(entidadId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());            
}

// metodo convertToDTO
private DenunciaResponseDTO convertToDTO(Denuncias denuncia){
    if(denuncia == null){
        return null;
    }
    try{
        String nombreDenunciante = null;
        if(denuncia.getDenunciante() != null && denuncia.getDenunciante().getNombre() != null){
            NomCompleto nombreCompleto = denuncia.getDenunciante().getNombre();
            nombreDenunciante = (nombreCompleto.getNombre() != null ? nombreCompleto.getNombre(): "") + " " +
                                (nombreCompleto.getApellidoP() != null ? nombreCompleto.getApellidoP(): "") + " " +
                                (nombreCompleto.getApellidoM() != null ? nombreCompleto.getApellidoM(): "");
            nombreDenunciante = nombreDenunciante.trim();
            if(nombreDenunciante.isEmpty()){
                nombreDenunciante = null;
            }
        }
        String nombreAcusado = "";
        String apellidoPAcusado = "";
        String apellidoMAcusado = "";
        if (denuncia.getNombreAcusado() != null) {
                    nombreAcusado = denuncia.getNombreAcusado().getNombre() != null ? denuncia.getNombreAcusado().getNombre() : "";
                    apellidoPAcusado = denuncia.getNombreAcusado().getApellidoP() != null ? denuncia.getNombreAcusado().getApellidoP() : "";
                    apellidoMAcusado = denuncia.getNombreAcusado().getApellidoM() != null ? denuncia.getNombreAcusado().getApellidoM() : "";
                }
        
        String entidadAcademicaNombre = null;
        if(denuncia.getEntidadAcademica() != null){
            entidadAcademicaNombre = denuncia.getEntidadAcademica().getNombre();
        }
        String procedimientoNombre = null;
        if(denuncia.getProceso() != null && denuncia.getProceso().getTipoProcedimiento() != null){
            procedimientoNombre = denuncia.getProceso().getTipoProcedimiento().getNombreProcedimiento();
        }

        DenunciaResponseDTO dto = new DenunciaResponseDTO();
        dto.setDenunciaId(denuncia.getDenunciaId());
        dto.setNombreDenunciante(nombreDenunciante);
        dto.setNombreAcusado(nombreAcusado);
        dto.setApellidoPAcusado(apellidoPAcusado);
        dto.setApellidoMAcusado(apellidoMAcusado);
        dto.setDescripcion(denuncia.getDescripcion() != null ? denuncia.getDescripcion() : "");
        dto.setLugarHechos(denuncia.getLugarHechos() != null ? denuncia.getLugarHechos() : "");
        dto.setRelacion(denuncia.getRelacion() != null ? denuncia.getRelacion() : "");
        dto.setEntidadAcademicaNombre(entidadAcademicaNombre);
        dto.setEstado(denuncia.getEstado());
        dto.setProcedimientoNombre(procedimientoNombre);
        
        return dto;
                
    } catch (Exception e) {
        System.err.println("Error en convertToDTO para denuncia ID: " + denuncia.getDenunciaId());
        e.printStackTrace();
        return null;
    }
  }
}

// checar service, regresa json vacio
/// modificaciones de hoy
/// app.properties se agregaron logs 
/// se qutio la parte de spring security del pom
/// checar la compilacion
/// la main igual se le agrego algo del spring security