package com.UniversidaVeracruzana.buzondenuncias.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoNotificacion;
import com.UniversidaVeracruzana.buzondenuncias.model.Notificaciones;
import com.UniversidaVeracruzana.buzondenuncias.model.Usuarios;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Integer>{

    // Encuentra todas las notificaciones para un destinatario***
    // consulta para el "buzón".
    List<Notificaciones> findByDestinatarioOrderByFechaNotificacionDesc(Usuarios destinatario);
    
    // cuenta las notificaciones no leídas de un usuario (posible icono de campana)
    long countByDestinatarioAndEstadoNotificacion(Usuarios destinatario, EstadoNotificacion estado);

}
