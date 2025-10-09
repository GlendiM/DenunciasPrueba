package com.UniversidaVeracruzana.buzondenuncias.converter;

import com.UniversidaVeracruzana.buzondenuncias.enums.TipoRegiones;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Convertidor para el enum TipoRegiones
@Converter(autoApply = true) //indica que se use automaticamente para la entidad
public class TipoRegionesConverter implements AttributeConverter<TipoRegiones, String>{
    
    @Override
    public String convertToDatabaseColumn(TipoRegiones region) {
        if(region == null){
            return null;
        }
        // mapeo de enum a string
        return switch(region){
            case Orizaba_Cordoba -> "Orizaba-Córdoba";
            case Coatzacoalcos_Minatitlan -> "Coatzacoalcos-Minatitlán";
            case Poza_Rica_Tuxpan -> "Poza Rica-Tuxpan";
            default -> region.name(); // los demas se mantienen igual
        };
    }

    @Override
    public TipoRegiones convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        // mapeo de string a enum
        return switch(dbData){
            case "Orizaba-Córdoba" -> TipoRegiones.Orizaba_Cordoba;
            case "Coatzacoalcos-Minatitlán" -> TipoRegiones.Coatzacoalcos_Minatitlan;
            case "Poza Rica-Tuxpan" -> TipoRegiones.Poza_Rica_Tuxpan;
            default -> TipoRegiones.valueOf(dbData); // los demas se mantienen igual
        };
    }

}
