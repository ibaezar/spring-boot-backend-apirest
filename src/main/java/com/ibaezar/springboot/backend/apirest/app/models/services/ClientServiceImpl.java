package com.ibaezar.springboot.backend.apirest.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibaezar.springboot.backend.apirest.app.models.dao.IClientDao;
import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    private IClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }
    
}
