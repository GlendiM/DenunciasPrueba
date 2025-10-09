package com.UniversidaVeracruzana.converter;


import com.UniversidaVeracruzana.enums.RolesEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<RolesEnum, String> {

    @Override
    public String convertToDatabaseColumn(RolesEnum rol){
        if(rol == null){
            return null;
        }
        return rol.getCodigo(); // guarda "RL_ALU" en la base
    }

    @Override
    public RolesEnum convertToEntityAttribute(String codigo){
        return RolesEnum.fromCode(codigo); // Lee "RL_ALU" de la base y se convierte a ROLESENUM.ALUMNO
    }

}
