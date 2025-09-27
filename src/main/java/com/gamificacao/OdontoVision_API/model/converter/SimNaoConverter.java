package com.gamificacao.OdontoVision_API.model.converter;

import com.gamificacao.OdontoVision_API.model.enums.SimNao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SimNaoConverter implements AttributeConverter<SimNao, String> {

    @Override
    public String convertToDatabaseColumn(SimNao attribute) {
        return attribute == null ? null : String.valueOf(attribute.getCodigo());
    }

    @Override
    public SimNao convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }
        return SimNao.fromCodigo(dbData.charAt(0));
    }
}
