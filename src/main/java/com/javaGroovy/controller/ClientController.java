package com.javaGroovy.controller;

import com.javaGroovy.domain.ClientRequest;
import com.javaGroovy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientRequest data(@RequestBody ClientRequest clientRequest){
       return clientService.createClient(clientRequest);
    }

}
