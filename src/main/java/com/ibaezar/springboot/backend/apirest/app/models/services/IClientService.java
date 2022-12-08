package com.ibaezar.springboot.backend.apirest.app.models.services;

import java.util.List;

import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;

public interface IClientService {
    public List<Client> findAll();
}
