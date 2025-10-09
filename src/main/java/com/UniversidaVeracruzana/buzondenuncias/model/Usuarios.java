package com.UniversidaVeracruzana.buzondenuncias.model;

import com.UniversidaVeracruzana.buzondenuncias.enums.RolesEnum;
import com.UniversidaVeracruzana.buzondenuncias.enums.Status_User;
import com.UniversidaVeracruzana.buzondenuncias.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Usuarios")
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_user")
    private TipoUsuario tipoUser;

    @Column(name = "matricula", unique = true, length = 30)
    private String matricula;

    @Column(name = "numero_personal", unique = true, length = 40)
    private String numeroPersonal;
    
    @Embedded
    private NomCompleto nombre;

    @Column(name = "correo_electronunico", unique = false, length = 100)
    private String correoElectronico;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "sexo", length = 20)
    private String sexo;

    @Column(name = "Identidad_sexogenerica", length = 50)
    private String identidadSexogenerica;

    @Column(name = "dependencia_adscripcion", length = 100)
    private String dependenciaAdscripcion;

    @Column(name = "contacto", length = 16)
    private String contacto;

    @Column(name = "rolFK", nullable = false)
    private RolesEnum rol; //converter

    @Column(name = "statusFK", nullable = false) 
    private Status_User status; 
    
    // Relaciones / llaves foraneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionfk")
    private Regiones region; //converter

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Entidad_academica")
    private EntidadAcademica entidadAcademica;

}
