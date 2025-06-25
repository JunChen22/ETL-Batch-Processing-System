package io.github.jun.dto;

import io.github.jun.dto.model.EndpointDTO;
import io.github.jun.entity.Endpoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOMapper {

    EndpointDTO endpointToDTO(Endpoint endpoint);
}
