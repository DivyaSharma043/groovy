package com.javaGroovy.service;

import com.javaGroovy.domain.ClientRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public ClientRequest createClient(ClientRequest clientRequest) {
        System.out.println(clientRequest);
        return clientRequest;
    }

}
