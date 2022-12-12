package com.ibaezar.springboot.backend.apirest.app.models.services;

import java.util.List;

import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;

public interface IClientService {
    public List<Client> findAll();

    public Client findById(Long id);

    public Client save(Client client);

    public void delete(Long id);
}
