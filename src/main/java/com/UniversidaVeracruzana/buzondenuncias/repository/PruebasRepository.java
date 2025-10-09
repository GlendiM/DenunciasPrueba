package com.UniversidaVeracruzana.buzondenuncias.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.model.Denuncias;
import com.UniversidaVeracruzana.buzondenuncias.model.Pruebas;


@Repository
public interface PruebasRepository extends JpaRepository<Pruebas, Integer> {

    // encuentra todas las pruebas x denuncia
    List<Pruebas> findByDenuncia(Denuncias denuncia);
}
