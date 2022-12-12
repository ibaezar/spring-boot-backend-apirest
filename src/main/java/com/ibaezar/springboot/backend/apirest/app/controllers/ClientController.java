package com.ibaezar.springboot.backend.apirest.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;
import com.ibaezar.springboot.backend.apirest.app.models.services.IClientService;

@CrossOrigin(origins = {"http://localhost:4200/"}, methods = {RequestMethod.GET})
@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private IClientService clientService;
    
    @GetMapping("/listar")
    public List<Client> index(){
        return clientService.findAll();
    }

    @GetMapping("/detalle/{id}")
    public Client show(@PathVariable Long id){
        return clientService.findById(id);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client, @PathVariable Long id){
        Client actualClient = clientService.findById(id);

        actualClient.setName(client.getName());
        actualClient.setLastname(client.getLastname());
        actualClient.setEmail(client.getEmail());

        return clientService.save(actualClient);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }
}
