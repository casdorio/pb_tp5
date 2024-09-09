package com.carlos.infnet.client_service.service;

import java.util.List;
import java.util.Optional;

import com.carlos.infnet.client_service.model.Client;

public interface ClientService {
    
    Client create(Client client);
    
    List<Client> findAll();
    
    Optional<Client> findById(Long id);
    
    void delete(Long id);
    
    Client update(Long id, Client client);
}
