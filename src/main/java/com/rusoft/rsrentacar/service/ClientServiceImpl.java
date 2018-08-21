package com.rusoft.rsrentacar.service;

import com.rusoft.rsrentacar.domain.Client;
import com.rusoft.rsrentacar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client add(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void deleteById(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long clientId) {
        Optional<Client> opt = clientRepository.findById(clientId);
        return opt.orElse(null);
    }

    @Override
    public Client findByNameAndYear(String name, String year) {
        return clientRepository.findByNameAndYear(name, year);
    }
}
