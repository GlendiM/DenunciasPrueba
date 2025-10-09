package com.UniversidaVeracruzana.buzondenuncias.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.model.Denuncias;
import com.UniversidaVeracruzana.buzondenuncias.model.Seguimiento;


@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
    
    // encuentra todo el historial de seguimiento para una denuncia específica
    List<Seguimiento> findByDenunciaOrderByFechaSeguimientoDesc(Denuncias denuncia);

}
