package com.UniversidaVeracruzana.buzondenuncias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.model.EntidadAcademica;

@Repository
public interface EntidadAcademicaRepository extends JpaRepository<EntidadAcademica, Integer> {
    // no se necesitan metodos personalizados para esta tabla
}
