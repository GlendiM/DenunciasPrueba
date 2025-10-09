package com.UniversidaVeracruzana.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Seguimiento")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeguimientoID")
    private Integer seguimientoId;

    @Column(name = "fecha_seguimiento")
    private LocalDateTime fechaSegumiento;
    
    @Column(name = "accion_tomada", nullable = false, columnDefinition = "TEXT")
    private String accionTomada;

    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;

    // Relaciones / llaves foraneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denunciaFK")
    private Denuncias denuncia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsableFk")
    private Usuarios responsable;

}
