package com.UniversidaVeracruzana.buzondenuncias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "tipoProcedimiento")
public class TipoProcedimiento {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "tipoProcedimientoID")
    private Integer tipoProcedimientoId;

    @Column(name = "nombreProcedimiento", nullable = false, length = 100)
    private String nombreProcedimiento;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

}
