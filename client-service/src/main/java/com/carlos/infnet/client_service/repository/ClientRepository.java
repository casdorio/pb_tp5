package com.carlos.infnet.client_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlos.infnet.client_service.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>  {

}
