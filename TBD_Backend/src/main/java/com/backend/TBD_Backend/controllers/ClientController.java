package com.backend.TBD_Backend.controllers;

import com.backend.TBD_Backend.models.ClientEntity;
import com.backend.TBD_Backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientEntity> listAll() {
        return clientService.findAll();
    }

    @PostMapping
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientEntity client) {
        return ResponseEntity.ok( clientService.saveClient(client));
    }
}
