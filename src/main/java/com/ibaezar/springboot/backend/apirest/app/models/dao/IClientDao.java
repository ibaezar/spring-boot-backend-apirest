package com.ibaezar.springboot.backend.apirest.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;

public interface IClientDao extends CrudRepository<Client, Long>{
    
}
