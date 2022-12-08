package com.ibaezar.springboot.backend.apirest.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;
import com.ibaezar.springboot.backend.apirest.app.models.services.IClientService;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private IClientService clientService;
    
    @GetMapping("/listar")
    public List<Client> index(){
        return clientService.findAll();
    }
}
