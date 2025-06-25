package io.github.jun.controller;

import io.github.jun.dto.model.EndpointDTO;
import io.github.jun.service.impl.EndpointServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final EndpointServiceImpl endpointService;

    public TestController(EndpointServiceImpl endpointService) {
        this.endpointService = endpointService;
    }

    @GetMapping("/")
    public String hello() {
        return "hello there";
    }

    @GetMapping("/endpoint")
    public List<EndpointDTO> listAllEndpoints() {
        return endpointService.listAllEndpointDTO();
    }
}
