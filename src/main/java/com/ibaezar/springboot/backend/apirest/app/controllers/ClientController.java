package com.ibaezar.springboot.backend.apirest.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibaezar.springboot.backend.apirest.app.models.entity.Client;
import com.ibaezar.springboot.backend.apirest.app.models.services.IClientService;

//@CrossOrigin(origins = {"http://localhost:4200/"}, methods = {RequestMethod.GET})
@CrossOrigin
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
    public ResponseEntity<?> show(@PathVariable Long id){

        Client client = null;

        Map<String, Object> response = new HashMap<>();

        try {
            client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(client == null){
            response.put("message", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody Client client){
        Client clientNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            clientNew = clientService.save(client);
        } catch (DataAccessException e) {
            response.put("message", "Error al crear el usuario en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Cliente creado con éxito");
        response.put("client", clientNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id){

        Client actualClient = null;
        Map<String, Object> response = new HashMap<>();

        try {
            actualClient = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error: cliente no encontrado");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (actualClient != null) {
            actualClient.setName(client.getName());
            actualClient.setLastname(client.getLastname());
            actualClient.setEmail(client.getEmail());
        }

        try {
            clientService.save(actualClient);
        } catch (DataAccessException e) {
            response.put("message", "Error: no fue posible actualizar el cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("client", actualClient);
        response.put("message", "Cliente actualizado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            clientService.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al eliminar al cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Cliente eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
