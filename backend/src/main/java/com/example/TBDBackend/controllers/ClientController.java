package com.example.TBDBackend.controllers;

import com.example.TBDBackend.entities.ClientEntity;
import com.example.TBDBackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable String id) {
        return new ResponseEntity<>(clientService.findClientById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClientById(@PathVariable String id) {
        clientService.deleteClientById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
