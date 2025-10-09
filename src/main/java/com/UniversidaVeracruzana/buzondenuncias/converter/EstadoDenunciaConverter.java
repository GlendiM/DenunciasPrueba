package com.UniversidaVeracruzana.buzondenuncias.converter;

import com.UniversidaVeracruzana.buzondenuncias.enums.EstadoDenuncia;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoDenunciaConverter implements AttributeConverter<EstadoDenuncia, String>{
    @Override
    public String convertToDatabaseColumn(EstadoDenuncia estado){
        if(estado == null){
            return null;
        }
        return estado.getValorEnDB(); // guarda "En revision" en la base
    }

    @Override
    public EstadoDenuncia convertToEntityAttribute(String valorEnDB){
        return EstadoDenuncia.fromDbValue(valorEnDB);
    }
}
