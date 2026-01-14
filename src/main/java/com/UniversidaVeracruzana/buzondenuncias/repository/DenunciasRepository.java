package com.UniversidaVeracruzana.buzondenuncias.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoDenuncia;
import com.UniversidaVeracruzana.buzondenuncias.model.Denuncias;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;

@Repository
public interface DenunciasRepository extends JpaRepository<Denuncias, Integer>{
    // Encuentra todas las denuncias presentadas por un usuario específico
    List<Denuncias> findByDenunciante(Usuarios denunciante);

    // Encuentra todas las denuncias con un estado particular
    List<Denuncias> findByEstado(EstadoDenuncia estado);

    // Encuentra todas las denuncias de una entidad académica
    // (para el dashboard del coordinador)
    List<Denuncias> findByEntidadAcademica_EntidadId(Integer entidadId);

}
