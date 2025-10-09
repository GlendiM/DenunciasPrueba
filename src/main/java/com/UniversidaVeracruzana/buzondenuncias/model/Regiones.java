package com.UniversidaVeracruzana.buzondenuncias.model;

import com.UniversidaVeracruzana.buzondenuncias.enums.TipoRegiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Regiones")
public class Regiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "RegionID")
    private Integer regionId;

    @Column(name = "region", nullable = false)
    private TipoRegiones region; //usa el converter  


}
