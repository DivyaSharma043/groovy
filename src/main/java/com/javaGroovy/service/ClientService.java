package com.javaGroovy.service;

import com.javaGroovy.domain.ClientRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientService {

    public ClientRequest createClient(ClientRequest clientRequest) {
//        ClientRequest clientRequest = new ClientRequest();
//        clientRequest.setRequestUuid("123");
//        clientRequest.setUseCaseId("lpl");
//
//        ClientRequestData clientRequestData = new ClientRequestData();
//        clientRequestData.setSchemaId("text");
//        clientRequestData.setMetaDataMap(hashData());
//        clientRequest.setClientRequestData(clientRequestData);
        System.out.println(clientRequest);
        return clientRequest;
    }
    private List<HashMap<String, String>> hashData() {
        List<HashMap<String, String>> metaList = new ArrayList<>();
        HashMap<String, String> metaData = new HashMap<>();

        metaData.put("test", "test1");
        metaData.put("status", "oos");
        HashMap<String, String> metaData2 = new HashMap<>();
        metaData2.put("test", "test2");
        metaData2.put("status", "oos");

        metaList.add(metaData);
        metaList.add(metaData2);
        return metaList;
    }

}
