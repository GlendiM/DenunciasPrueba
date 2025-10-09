package com.UniversidaVeracruzana.buzondenuncias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "Pruebas")
public class Pruebas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PruebaID")
    private Integer pruebaId;

    @Column(name = "tipo_prueba", length = 60)
    private String tipoPrueba;

    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;

    @Lob
    @Column(name = "archivo", columnDefinition = "BYTEA")
    private byte[] archivo;

    //relaciones / llaves foraneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denunciaFK")
    private Denuncias denuncia;
    
}
