package ru.kke.springbootcourse.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import ru.kke.springbootcourse.model.AddressDto;
import ru.kke.springbootcourse.model.entity.AddressEntity;

@Mapper(componentModel = SPRING)
public interface AddressMapper {

    AddressDto toDto(Address address);

    @InheritInverseConfiguration
    AddressEntity toEntity(AddressDto addressDto);

}
