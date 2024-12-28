package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.AddressRequest;
import com.example.TBDBackend.models.Address;
import com.example.TBDBackend.models.Client;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(String id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }

        return client.get();
    }

    public void deleteClientById(String id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found");
        }

        clientRepository.deleteById(id);
    }
    public Client addAddress(String clientId, AddressRequest addressRequest) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Address address = new Address();
        address.setStreetAddress(addressRequest.getStreetAddress());
        address.setLocation(new GeoJsonPoint(
                addressRequest.getLongitude(),
                addressRequest.getLatitude()
        ));

        if (client.getAddresses() == null) {
            client.setAddresses(new ArrayList<>());
        }
        client.getAddresses().add(address);

        return clientRepository.save(client);
    }

    public ResponseEntity<List<Address>> findAddressesByClientId(String clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Cliente no encontrado");
        }
        List<Address> addresses = new ArrayList<>();
        clientRepository.findById(clientId)
                .ifPresent(client -> addresses.addAll(client.getAddresses()));
        return ResponseEntity.ok(addresses);
    }
}
