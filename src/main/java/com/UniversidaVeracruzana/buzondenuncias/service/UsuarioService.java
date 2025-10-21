package com.UniversidaVeracruzana.buzondenuncias.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.UniversidaVeracruzana.buzondenuncias.dto.LoginRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.enums.RolesEnum;
import com.UniversidaVeracruzana.buzondenuncias.mapper.UsuariosMapper;
import com.UniversidaVeracruzana.buzondenuncias.model.EntidadAcademica;
import com.UniversidaVeracruzana.buzondenuncias.model.Regiones;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;
import com.UniversidaVeracruzana.buzondenuncias.repository.EntidadAcademicaRepository;
import com.UniversidaVeracruzana.buzondenuncias.repository.RegionRepository;
import com.UniversidaVeracruzana.buzondenuncias.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final RegionRepository regionRepository;
    private final EntidadAcademicaRepository academicaRepository;
    private final UsuariosMapper usuarioMapper;

    // CONSULTA GENERAL
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }
    // CONSULTA POR ID
    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> findById(Integer Id){
        return usuarioRepository.findById(Id)
                .map(usuarioMapper::toDto);
    }

    // CREAR USUARIO
    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO) {
        // Validar unicidad de correo usando findByCorreoElectronico
        if (usuarioRepository.findByCorreoElectronico(usuarioRequestDTO.getCorreoElectronico()).isPresent()){
            throw new RuntimeException("El correo electronico ya está registrado");
        }
        // Buscar y asignar region y entidad academica
        Regiones region = null;
        if(usuarioRequestDTO.getRegionId() != null){
            region = regionRepository.findById(usuarioRequestDTO.getRegionId())
                    .orElseThrow(() -> new RuntimeException("Región No Encontrada"));
        }
        EntidadAcademica entidadAcademica = null;
        if(usuarioRequestDTO.getEntidadAcademicaId() != null){
            entidadAcademica = academicaRepository.findById(usuarioRequestDTO.getEntidadAcademicaId())
                                .orElseThrow(() -> new RuntimeException("Entidad Academica no Encontrada"));
        }

        Usuarios usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        usuario.setRegion(region);
        usuario.setEntidadAcademica(entidadAcademica);

        Usuarios savedUsuarios = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(savedUsuarios);
    }

    // ACTUALIZAR USUARIO 
    public UsuarioResponseDTO update(Integer id, UsuarioRequestDTO usuarioRequestDTO){
        Usuarios existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));

        // validar  que el nuevo correo no este en uso por otro usuario
        if(usuarioRequestDTO.getCorreoElectronico() != null &&
            !usuarioRequestDTO.getCorreoElectronico().equals(existingUsuario.getCorreoElectronico())) {
                Optional<Usuarios> usuarioConCorreo = usuarioRepository.findByCorreoElectronico(usuarioRequestDTO.getCorreoElectronico());
                if(usuarioConCorreo.isPresent() && !usuarioConCorreo.get().getUsuarioId().equals(id)) {
                    throw new RuntimeException("El Correo Electrónico ya está en uso por otro usuario");
            }
        }
        if (usuarioRequestDTO.getNombre() != null) {
            existingUsuario.getNombre().setNombre(usuarioRequestDTO.getNombre());            
        }
        if (usuarioRequestDTO.getApellidoP() != null) {
            existingUsuario.getNombre().setApellidoP(usuarioRequestDTO.getApellidoP());
        }
        if (usuarioRequestDTO.getApellidoM() != null) {
            existingUsuario.getNombre().setApellidoM(usuarioRequestDTO.getApellidoM());
        }
        if (usuarioRequestDTO.getCorreoElectronico() != null) {
            existingUsuario.setCorreoElectronico(usuarioRequestDTO.getCorreoElectronico());            
        }
        if (usuarioRequestDTO.getEdad() != null) {
            existingUsuario.setEdad(usuarioRequestDTO.getEdad());            
        }
        if (usuarioRequestDTO.getSexo() != null) {
            existingUsuario.setSexo(usuarioRequestDTO.getSexo());
        }
        if (usuarioRequestDTO.getIdentidadSexogenerica() != null) {
            existingUsuario.setIdentidadSexogenerica(usuarioRequestDTO.getIdentidadSexogenerica());
        }
        if (usuarioRequestDTO.getContacto() != null) {
            existingUsuario.setContacto(usuarioRequestDTO.getContacto());            
        }
        if (usuarioRequestDTO.getDependenciaAdscripcion() != null) {
            existingUsuario.setDependenciaAdscripcion(usuarioRequestDTO.getDependenciaAdscripcion());           
        }
        
        // Actualizar relaciones
        if(usuarioRequestDTO.getRegionId() != null){
            Regiones region = regionRepository.findById(usuarioRequestDTO.getRegionId())
                    .orElseThrow(()-> new RuntimeException("Región no Encontrada"));
            existingUsuario.setRegion(region);
        } 
        if (usuarioRequestDTO.getEntidadAcademicaId() != null) {
            EntidadAcademica entidadAcademica = academicaRepository.findById(usuarioRequestDTO.getEntidadAcademicaId())
                            .orElseThrow(()-> new RuntimeException("Entidad Académica no Encontrada"));
            existingUsuario.setEntidadAcademica(entidadAcademica);
        }
        Usuarios updateUsuarios = usuarioRepository.save(existingUsuario);
        return usuarioMapper.toDto(updateUsuarios);
    }

    // ELIMINAR USUARIO
    public void deleteById(Integer id){
        usuarioRepository.deleteById(id);
    }

    // LOGIN
    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> login(LoginRequestDTO loginRequest){
        return usuarioRepository.findByCorreoElectronico(loginRequest.getCorreoElectronico())
                .filter(usuario -> usuario.getPassword().equals(loginRequest.getPassword()))
                .map(usuarioMapper::toDto);
    }

    //CONSULTAS ESPECIFICAAS - REPOSITORY
    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> findByCorreo(String correoElectronico){
        return usuarioRepository.findByCorreoElectronico(correoElectronico)
                .map(usuarioMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> findByNumeroPersonal(String numeroPersonal){
        return usuarioRepository.findByNumeroPersonal(numeroPersonal)
                .map(usuarioMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> findByCoordinadorByEntidad(RolesEnum rol, Integer entidadAcademicaId){
        EntidadAcademica entidadAcademica2 = academicaRepository.findById(entidadAcademicaId)
                .orElseThrow(() -> new RuntimeException("Entidad Academica no Encontrada"));
        return usuarioRepository.findByRolAndEntidadAcademica(rol, entidadAcademica2)
                .map(usuarioMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findByRol(RolesEnum rol){
        return usuarioRepository.findAll()
                .stream().filter(usuario -> usuario.getRol() == rol)
                .map(usuarioMapper::toDto).collect(Collectors.toList());
    }
} 
