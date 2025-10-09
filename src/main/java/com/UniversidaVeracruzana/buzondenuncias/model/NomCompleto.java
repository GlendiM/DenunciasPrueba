package com.UniversidaVeracruzana.buzondenuncias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable // puede ser metida en otras clases 
public class NomCompleto {

    @Column(name =  "Nombre")
    private String nombre;

    @Column(name = "ApellidoP")
    private String apellidoP;

    @Column(name = "ApellidoM")
    private String apellidoM;

}
