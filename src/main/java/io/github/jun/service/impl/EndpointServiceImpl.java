package io.github.jun.service.impl;

import io.github.jun.dto.DTOMapper;
import io.github.jun.dto.model.EndpointDTO;
import io.github.jun.repository.EndpointRepository;
import io.github.jun.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointServiceImpl implements EndpointService {

    private final EndpointRepository endpointRepository;

    private final DTOMapper mapper;

    @Autowired
    public EndpointServiceImpl(EndpointRepository endpointRepository, DTOMapper mapper) {
        this.endpointRepository = endpointRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EndpointDTO> listAllEndpointDTO() {
        return endpointRepository.findAll().stream()
                .map(mapper::endpointToDTO)
                .toList();
    }
}
