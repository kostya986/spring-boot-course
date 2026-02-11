package ru.kke.springbootcourse.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kke.springbootcourse.model.BankBookDto;
import ru.kke.springbootcourse.model.entity.BankBookEntity;

@Mapper(componentModel = SPRING)
public interface BankBookMapper {
    @Mapping(target = "currency", source = "currency.name")
    @Mapping(target = "userId", source = "user.id")
    BankBookDto toDto(BankBookEntity entity);

    List<BankBookDto> toDtoList(List<BankBookEntity> entities);

    @InheritInverseConfiguration
    BankBookEntity toEntity(BankBookDto dto);
}
