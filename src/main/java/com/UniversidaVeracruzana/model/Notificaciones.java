package com.UniversidaVeracruzana.model;

import java.time.LocalDateTime;
import com.UniversidaVeracruzana.enums.EstadoNotificacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Notificaciones")
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificacionID")
    private Integer notificacionId;

    @Column(name = "tipo_noti", length = 50)
    private String tipoNoti;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_notificacion")
    private LocalDateTime fechaNotificacion;

    @Column(name = "medio_notificacion", length = 50)
    private String medioNotificacion;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_notificacion", length = 20)
    private EstadoNotificacion estadoNotificacion = EstadoNotificacion.ENVIADA;
    
    // Relaciones / llaves foraneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denunciaFK")
    private Denuncias denuncia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatarioFK")
    private Usuarios destinatario;

}
