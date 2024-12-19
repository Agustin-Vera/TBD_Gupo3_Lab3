package com.backend.TBD_Backend.services;

import com.backend.TBD_Backend.models.ClientEntity;
import com.backend.TBD_Backend.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    public ClientEntity saveClient(ClientEntity client) {
        return clientRepository.save(client);
    }
}
