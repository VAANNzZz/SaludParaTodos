package com.exe.app.services;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.exe.app.entity.Persona;

@Mapper(componentModel = "spring")
public interface PersonaMapper{
    PersonaMapper INSTANCE = Mappers.getMapper( PersonaMapper.class );
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    
    void updatePersonaFromDTO(PersonaDTO dto, @MappingTarget Persona persona);
}
