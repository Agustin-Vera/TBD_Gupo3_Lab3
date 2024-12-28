package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.AddressRequest;
import com.example.TBDBackend.models.Address;
import com.example.TBDBackend.models.Client;
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
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        return new ResponseEntity<>(clientService.findClientById(id), HttpStatus.OK);
    }

    @PostMapping("/{clientId}/addresses")
    public ResponseEntity<Client> addAddress(
            @PathVariable String clientId,
            @RequestBody AddressRequest request) {
        return ResponseEntity.ok(clientService.addAddress(clientId, request));
    }

    @GetMapping("/{clientId}/addresses")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable String clientId) {
        return clientService.findAddressesByClientId(clientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClientById(@PathVariable String id) {
        clientService.deleteClientById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
