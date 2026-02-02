package ru.kke.springbootcourse.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kke.springbootcourse.model.UserDto;
import ru.kke.springbootcourse.model.entity.UserEntity;

@Mapper(componentModel = SPRING, uses = {AddressMapper.class})
public interface UserMapper {

    @Mapping(target = "username", source = "name")
    UserDto toDto(UserEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "name", source = "username")
    UserEntity toEntity(UserDto dto);
}
