package com.javaGroovy;

import com.google.gson.Gson;
import com.javaGroovy.domain.ClientRequest;
import com.javaGroovy.domain.ClientRequestData;
import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.util.Eval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ClientServiceTest {

    public ClientRequest createClient() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestUuid("123");
        clientRequest.setUseCaseId("lpl");

        ClientRequestData clientRequestData = new ClientRequestData();
        clientRequestData.setSchemaId("text");
        clientRequestData.setMetaDataMap(hashData());
        clientRequest.setClientRequestData(clientRequestData);
        return clientRequest;
    }

    private List<HashMap<String, String>> hashData() {
        List<HashMap<String, String>> metaList = new ArrayList<>();
        HashMap<String, String> metaData = new HashMap<>();

        metaData.put("test", "test1");
        metaData.put("status", "H");
        HashMap<String, String> metaData2 = new HashMap<>();
        metaData2.put("test", "test2");
        metaData2.put("status", "H");
        HashMap<String, String> metaData3 = new HashMap<>();
        metaData3.put("test", "test3");
        metaData3.put("status", "done");
        HashMap<String, String> metaData4 = new HashMap<>();
        metaData4.put("test", "test4");
        metaData4.put("status", "L");

        metaList.add(metaData);
        metaList.add(metaData2);
        metaList.add(metaData3);
        metaList.add(metaData4);
        return metaList;
    }

    @Test
    public void test1() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestUuid("123");
        clientRequest.setUseCaseId("lpl");

        ClientRequestData clientRequestData = new ClientRequestData();
        clientRequestData.setSchemaId("text");
        clientRequestData.setMetaDataMap(hashData());
        clientRequest.setClientRequestData(clientRequestData);

        ClientRequest data = createClient();
        Assertions.assertEquals(clientRequest, data);
    }

    @Test
    public void test2() {
        ClientRequest data = createClient();
        List<HashMap<String, String>> mapData = data.getClientRequestData().getMetaDataMap();
        int count = 0;
        for (HashMap<String, String> data1 : mapData) {
            if ("H".equals(data1.get("status")) || ("L".equals(data1.get("status")))
                    && !"done".equals(data1.get("status"))) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println(count + "_abnormal test");
            for (HashMap<String, String> data1 : mapData) {
                if ("H".equals(data1.get("status")) || "L".equals(data1.get("status"))) {
                    System.out.println(data1);
                }
            }
        }
    }


    private Integer data() {
        ClientRequest data = createClient();
        List<HashMap<String, String>> mapData = data.getClientRequestData().getMetaDataMap();
        int count = 0;
        for (HashMap<String, String> data1 : mapData) {
            if (("H".equals(data1.get("status")) || "L".equals(data1.get("status")))
                    && !"done".equals(data1.get("status"))) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println(count + "_abnormal test");
            for (HashMap<String, String> data1 : mapData) {
                if ("H".equals(data1.get("status")) || "L".equals(data1.get("status"))) {
                    System.out.println(data1);
                }
            }
            return count;
        }
        return 0;
    }


    @Test
    public void test4() {
        ClientServiceTest clientServiceTest = new ClientServiceTest();
        String data = String.valueOf(clientServiceTest.createClient());
        Object ab = Eval.me(data);
        System.out.println(ab);
    }

    @Test
    public void test3() {
        ClientServiceTest clientServiceTest = new ClientServiceTest();
        ClientRequest clientRequest = clientServiceTest.createClient();
        List<HashMap<String, String>> mapList =
                clientRequest.getClientRequestData().getMetaDataMap();
        Binding binding = new Binding();
        binding.setVariable("clientRequest", clientRequest);
        binding.setVariable("mapData", mapList);
        String data = clientServiceTest.clientData(mapList);
        Object ab = Eval.me(data);
        System.out.println(ab);
    }

    private String clientData(List<HashMap<String, String>> mapData) {
        String condition = "int count = 0;\n" +
                "for (HashMap<String, String> data1 : " + mapData + ") {\n" +
                "    if ((\"H\".equals(data1.get(\"status\")) || \"L\"" +
                ".equals(data1.get(\"status\")))" +
                "            && !\"done\".equals(data1.get(\"status\"))) {\n" +
                "        count++;\n" +
                "    }\n" +
                "}\n" +
                "if (count > 0) {\n" +
                "    println(count + \"_abnormal test\");\n" +
                "    for (HashMap<String, String> data1 : " + mapData + ") {\n" +
                "        if (\"H\".equals(data1.get(\"status\")) || " +
                "\"L\".equals(data1.get(\"status\"))) {\n" +
                "            println(data1);\n" +
                "        }\n" +
                "    }\n" +
                "    return count;\n" +
                "}\n" +
                "return 0;";
        return condition;
    }

    private String clientData2(String json) {
        String condition = "int count = 0;\n" +
                "def mapData = new groovy.json.JsonSlurper().parseText('" + json + "')\n" +
                "for (data1 in mapData) {\n" +
                "    if ((\"H\".equals(data1['status']) || \"L\".equals(data1['status']))" +
                "            && !\"done\".equals(data1['status'])) {\n" +
                "        count++;\n" +
                "    }\n" +
                "}\n" +
                "if (count > 0) {\n" +
                "    println(count + \"_abnormal test\");\n" +
                "    for (data1 in mapData) {\n" +
                "        if (\"H\".equals(data1['status']) || \"L\".equals(data1['status'])) {\n" +
                "            println(data1);\n" +
                "        }\n" +
                "    }\n" +
                "    return count;\n" +
                "}\n" +
                "return 0;";
        return condition;
    }


    @Test
    public void test5() {
        // Creating a list of hashmaps for testing
        List<HashMap<String, String>> mapList = List.of(
                new HashMap<>(Map.of("test", "test1", "status", "H")),
                new HashMap<>(Map.of("test", "test2", "status", "L"))
        );

        // Converting the list of hashmaps to a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(mapList);
        System.out.println("JSON" + json);
        // Creating an instance of ClientServiceTest
        ClientServiceTest clientServiceTest = new ClientServiceTest();

        // Calling clientData to generate Groovy script
        String data = clientServiceTest.clientData2(json);

        // Executing the Groovy script using Eval.me
        Object ab = Eval.me(data);

        // Printing the result obtained from executing the Groovy script
        System.out.println(ab);
    }

    public String clientData3(String clientRequest) {
        System.out.println("Client: " + clientRequest);
        String condition =
                "def clientData = new groovy.json.JsonSlurper().parseText('" + clientRequest + "');\n" +
                        "List<HashMap<String, String>> mapData = clientData.clientRequestData.metaDataMap;\n" +
                        "int count = 0;\n" +
                        "System.out.println(mapData);\n" +
                        "for (HashMap<String, String> data1 : mapData) {\n" +
                        "    if ((\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\")))\n" +
                        "            && !\"done\".equals(data1.get(\"status\"))) {\n" +
                        "        count++;\n" +
                        "    }\n" +
                        "}\n" +
                        "if (count > 0) {\n" +
                        "    System.out.println(count + \"_abnormal test\");\n" +
                        "    for (HashMap<String, String> data1 : mapData) {\n" +
                        "        if (\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\"))) {\n" +
                        "            System.out.println(data1);\n" +
                        "        }\n" +
                        "    }\n" +
                        "    return count;\n" +
                        "}\n" +
                        "return 0;";

        return condition;
    }


    @Test
    public void test6() {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestUuid("123");
        clientRequest.setUseCaseId("test");

        List<HashMap<String, String>> mapList = List.of(
                new HashMap<>(Map.of("test", "test1", "status", "H")),
                new HashMap<>(Map.of("test", "test2", "status", "L"))
        );
        ClientRequestData clientRequestData = new ClientRequestData();
        clientRequestData.setMetaDataMap(mapList);
        clientRequest.setClientRequestData(clientRequestData);
        // Converting the list of hashmaps to a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(clientRequest);
        System.out.println("JSON" + json);
        // Creating an instance of ClientServiceTest
        ClientServiceTest clientServiceTest = new ClientServiceTest();

        // Calling clientData to generate Groovy script
        String data = clientServiceTest.clientData3(json);

        // Executing the Groovy script using Eval.me
        Object ab = Eval.me(data);

        // Printing the result obtained from executing the Groovy script
        System.out.println(ab);
    }

}
