package com.UniversidaVeracruzana.buzondenuncias.model;

import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoDenuncia;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "Denuncias")
public class Denuncias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DenunciaID")
    private Integer denunciaId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "nombre", column = @Column(name = "nombre_acusado")),
        @AttributeOverride(name = "apellidoP", column = @Column(name = "nombre_acusado_apellidoP")),
        @AttributeOverride(name = "apellidoM", column = @Column(name = "nombre_acusado_apellidoM"))
    })
    
    private NomCompleto nombreAcusado;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "lugar_hechos", nullable = false, length = 255)
    private String lugarHechos;

    @Column(name = "relacion", nullable = false, length = 100)
    private String relacion;

    @Column(name = "estado", length = 50)
    private EstadoDenuncia estado;

    // Relaciones / llaves foraneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DenuncianteFK")
    private Usuarios denunciante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_Academica")
    private EntidadAcademica entidadAcademica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procesoFK")
    private Procedimientos proceso; 

}
