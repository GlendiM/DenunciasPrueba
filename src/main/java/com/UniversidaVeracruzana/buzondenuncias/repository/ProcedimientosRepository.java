package com.UniversidaVeracruzana.buzondenuncias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UniversidaVeracruzana.buzondenuncias.model.Procedimientos;

@Repository
public interface ProcedimientosRepository extends JpaRepository<Procedimientos, Integer> {
    // no se necesitan metodos personalizados para esta tabla

}
