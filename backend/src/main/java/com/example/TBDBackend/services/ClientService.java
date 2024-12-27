package com.example.TBDBackend.services;

import com.example.TBDBackend.entities.ClientEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> findAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity findClientById(String id) {
        Optional<ClientEntity> client = clientRepository.findById(id);

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
}
