package com.UniversidaVeracruzana.buzondenuncias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.model.Regiones;

@Repository
public interface RegionRepository extends JpaRepository<Regiones, Integer> {
    // no se necesitan metodos personalizados para esta tabla
}
