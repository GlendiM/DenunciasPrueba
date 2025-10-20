package com.UniversidaVeracruzana.buzondenuncias.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.dto.LoginRequestDTO;
import com.UniversidaVeracruzana.buzondenuncias.dto.UsuarioResponseDTO;
import com.UniversidaVeracruzana.buzondenuncias.enums.RolesEnum;
import com.UniversidaVeracruzana.buzondenuncias.model.EntidadAcademica;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    Optional<UsuarioResponseDTO> login(LoginRequestDTO loginRequest);

    //Encuentra un usuario por su correo 
    Optional<Usuarios> findByCorreoElectronico(String correoElectronico);

    // Encuentra a un alumno por su matricula
    Optional<Usuarios> findByMatricula(String matricula);

    // Encuentra al personal por numero de empleado
    Optional<Usuarios> findByNumeroPersonal(String numeroPersonal);

    // Encontrar al coordinador de una entidad académica específica
    //IMPORTANTE
    Optional<Usuarios> findByRolAndEntidadAcademica(RolesEnum rol, EntidadAcademica entidadAcademica);
    Optional<UsuarioResponseDTO> findCoordinadorByEntidad(RolesEnum rol, Integer entidadAcademicaId);
    List<UsuarioResponseDTO> findByRol(RolesEnum rol);


}
