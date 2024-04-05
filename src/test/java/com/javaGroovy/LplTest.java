package com.javaGroovy;

import com.google.gson.Gson;
import com.javaGroovy.domain.ClientInputData;
import com.javaGroovy.domain.ClientRequest;
import com.javaGroovy.domain.ClientRequestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LplTest {

    private static String getData() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestUuid("123");
        clientRequest.setUseCaseId("test");

        ClientRequestData clientRequestData = new ClientRequestData();

        List<ClientInputData> clientInputDataList = new ArrayList<>();
        ClientInputData clientInputData1 = new ClientInputData();
        clientInputData1.setSchemaId("test1");
        clientInputData1.setIndex("1");

        HashMap<String, String> map = new HashMap<>();
        map.put("test", "test1");
        map.put("status", "H");

        clientInputData1.setMetaDataMap(map);
        clientInputDataList.add(clientInputData1);

        ClientInputData clientInputData2 = new ClientInputData();
        clientInputData2.setSchemaId("test2");
        clientInputData2.setIndex("2");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("test", "test2");
        map2.put("status", "L");

        clientInputData2.setMetaDataMap(map2);
        clientInputDataList.add(clientInputData2);

        clientRequestData.setClientInputData(clientInputDataList);
        clientRequest.setClientRequestData(clientRequestData);
        Gson gson = new Gson();
        String jsonClientRequest = gson.toJson(clientRequest);
        return jsonClientRequest;
    }

}
