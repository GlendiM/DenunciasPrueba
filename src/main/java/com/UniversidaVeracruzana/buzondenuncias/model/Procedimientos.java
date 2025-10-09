package com.UniversidaVeracruzana.buzondenuncias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "Procedimientos")
public class Procedimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProcedimientoID")
    private Integer procedimientoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_procedimientoFK", nullable = false, unique = true)
    private TipoProcedimiento tipoProcedimiento;

    @Column(name = "autoridad_responsable", columnDefinition = "TEXT")
    private String autoridadResponsable;

    @Column(name = "normativa_aplicable", columnDefinition = "TEXT")
    private String normativaAplicable;

}
