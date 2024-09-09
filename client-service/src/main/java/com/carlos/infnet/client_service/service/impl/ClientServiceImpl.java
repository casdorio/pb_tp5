package com.carlos.infnet.client_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carlos.infnet.client_service.model.Client;
import com.carlos.infnet.client_service.repository.ClientRepository;
import com.carlos.infnet.client_service.service.ClientService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente não encontrado: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(Long id, Client clientAlterado) {
        return clientRepository.findById(id).map(client -> {
            client.setName(clientAlterado.getName());
            client.setEmail(clientAlterado.getEmail());
            client.setPhone(clientAlterado.getPhone());
            client.setAddress(clientAlterado.getAddress());
            return clientRepository.save(client);
        }).orElseGet(() -> {
            clientAlterado.setId(id);
            return clientRepository.save(clientAlterado);
        });
    }
}
