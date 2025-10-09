package com.UniversidaVeracruzana.buzondenuncias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.model.TipoProcedimiento;

@Repository
public interface TipoProcedimientoRepository extends JpaRepository<TipoProcedimiento, Integer> {
    // no se necesitan metodos personalizados para esta tabla
}
