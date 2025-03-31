package com.aldis.clientRest.controller;


import com.aldis.clientRest.dto.ClientDTO;
import com.aldis.clientRest.dto.ClientListDTO;
import com.aldis.clientRest.entity.Client;
import com.aldis.clientRest.repository.Service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "API for client management")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping
    @Operation(summary = "Get all Clients", description = "Returns a list of customers\n")
    public List<ClientListDTO> getAll(@RequestHeader String token) throws AuthenticationException {
        return service.getAll(token);
    }

    @PostMapping
    @Operation(summary = "Create Client", description = "Endpoint for client creation")
    public Client create(@RequestHeader String token,@Valid @RequestBody ClientDTO clientDTO) throws AuthenticationException {
        return service.create(token,clientDTO);
    }

    @GetMapping("/metrics")
    @Operation(summary = "Get Metrics", description = "Endpoint to obtain the metrics")
    public ResponseEntity<Map<String, Object>> getMetrics(
            @RequestHeader String token
    ) throws AuthenticationException {
        Map<String, Object> metrics = service.getMetrics(token);
        return ResponseEntity.ok(metrics);
    }
}
