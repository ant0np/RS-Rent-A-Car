package com.rusoft.rsrentacar.service;

import com.rusoft.rsrentacar.domain.Client;

import java.util.List;

public interface ClientService {
    Client add(Client client);
    void delete(Client client);
    void deleteById(Long clientId);
    List<Client> findAll();
    Client findById(Long clientId);
    Client findByNameAndYear(String name, String year);
}
