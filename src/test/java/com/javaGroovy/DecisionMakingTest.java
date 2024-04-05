//package com.javaGroovy;
//
//import com.google.gson.Gson;
//import com.javaGroovy.domain.ClientRequest;
//import com.javaGroovy.domain.ClientRequestData;
//import groovy.lang.Binding;
//import groovy.lang.GroovyShell;
//import groovy.util.Eval;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class DecisionMakingTest {
//
//    private static String getString() {
//        ClientRequest clientRequest = new ClientRequest();
//        clientRequest.setRequestUuid("123");
//        clientRequest.setUseCaseId("test");
//
//        List<HashMap<String, String>> mapList = List.of(
//                new HashMap<>(Map.of("test", "test1", "status", "H")),
//                new HashMap<>(Map.of("test", "test2", "status", "L")),
//                new HashMap<>(Map.of("test", "test1", "status", "H")),
//                new HashMap<>(Map.of("test", "test2", "status", "L")),
//                new HashMap<>(Map.of("test", "test1", "status", "H")),
//                new HashMap<>(Map.of("test", "test2", "status", "L")),
//                new HashMap<>(Map.of("test", "test1", "status", "H")),
//                new HashMap<>(Map.of("test", "test2", "status", "L")),
//                new HashMap<>(Map.of("test", "test1", "status", "H"))
//        );
//        ClientRequestData clientRequestData = new ClientRequestData();
//        clientRequestData.setMetaDataMap(mapList);
//        clientRequest.setClientRequestData(clientRequestData);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(clientRequest);
//        return json;
//    }
//
//    private String dataCount(String data) {
//        String condition =
//                "def clientData = new groovy.json.JsonSlurper().parseText('" + data + "');\n" +
//                        "List<HashMap<String, String>> mapData = clientData.clientRequestData.metaDataMap;\n" +
//                        "int count = 0;\n" +
//                        "for (HashMap<String, String> data1 : mapData) {\n" +
//                        "    if ((\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\")))\n" +
//                        "            && !\"done\".equals(data1.get(\"status\"))) {\n" +
//                        "        count++;\n" +
//                        "    }\n" +
//                        "}\n" +
//                        "if (count > 0) {\n" +
//                        "    for (HashMap<String, String> data1 : mapData) {\n" +
//                        "        if (\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\"))) {\n" +
//                        "        }\n" +
//                        "    }\n" +
//                        "    return count;\n" +
//                        "}\n" +
//                        "return 0;\n";
//        return condition;
//    }
//
//    public String conditionMap(String clientData) {
//        String condition =
//                "def data = new groovy.json.JsonSlurper().parseText('" + clientData + "');\n" +
//                        "List<HashMap<String, String>> mapData = data.clientRequestData.metaDataMap;\n\n" +
//                        "System.out.println(\"MAP DATA: \" + mapData);\n" +
//                        "int count = 0;\n" +
//                        "for (HashMap<String, String> data1 : mapData) {\n" +
//                        "    if ((\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\")))\n" +
//                        "            && !\"done\".equals(data1.get(\"status\"))) {\n" +
//                        "        count++;\n" +
//                        "    }\n" +
//                        "}\n" +
//                        "HashMap<Integer, String> hashMap = new HashMap<>();\n" +
//                        "System.out.println(\"Abnormal count: \" + count);\n" +
//                        "String[] meltFiles = new String[]{\"mp4\", \"mlt2_1\", \"mlt2_2\", \"mlt2_3\", \"mlt2_4\"};\n" +
//                        "if (count >= 1 && count < meltFiles.length) {\n" +
//                        "    hashMap.put(count, meltFiles[count]);\n" +
//                        "} else if (count == 0) {\n" +
//                        "    hashMap.put(count, meltFiles[count]);\n" +
//                        "}\n" +
//                        "if (count > 4 && count <= 8) {\n" +
//                        "    String largestMlt = meltFiles[meltFiles.length - 1];\n" +
//                        "    String abnormalMlt = largestMlt + \" + mlt2_\" + (count - 4);\n" +
//                        "    hashMap.put(count, abnormalMlt);\n" +
//                        "} else if (count > 8 && count <= 12) {\n" +
//                        "    String largestMlt = meltFiles[meltFiles.length - 1];\n" +
//                        "    String abnormalMlt = largestMlt + \" + \" + largestMlt + \" + mlt2_\" + (count - 8);\n" +
//                        "    hashMap.put(count, abnormalMlt);\n" +
//                        "}  else if (count > 12){\n" +
//                        "    String largestMlt=meltFiles[meltFiles.length - 1];\n" +
//                        "    String abnormalMlt=largestMlt+\" + \" + largestMlt +\" + \"+ largestMlt + \"+ mlt2_\" + (count - 12);\n" +
//                        "    hashMap.put (count,abnormalMlt);\n" +
//                        "    System.out.println (\"After 12th mlt: \" + hashMap);\n" +
//                        "}\n" +
//                        "return hashMap.getOrDefault(count, \"UNKNOWN\");\n";
//        return condition;
//    }
//
//    @Test
//    public void test1() {
//        String json = getString();
//
//        DecisionMakingTest decisionMakingTest = new DecisionMakingTest();
//        String data = decisionMakingTest.dataCount(json);
//        Object ab = Eval.me(data);
//        System.out.println("ABNORMAL_TEST: " + ab);
//    }
//
//    @Test
//    public void test2() {
//        String json = getString();
//
//        DecisionMakingTest decisionMakingTest = new DecisionMakingTest();
//        String choosingMlt = decisionMakingTest.conditionMap(json);
//        Object getData = Eval.me(choosingMlt);
//
//        System.out.println("Mlt used: " + getData);
//    }
//
//    private String data(String clientData) {
//      String condition =   "def data = new groovy.json.JsonSlurper().parseText('" + clientData + "');\n" +
//                "        List<HashMap<String,String>> mapData = data.clientRequestData.metaDataMap;\n" +
//                "        System.out.println(\"MAPDATA:\" + mapData);\n" +
//                "        int count = 0;\n" +
//                "        for (HashMap<String,String> data1 : mapData) {\n" +
//                "            if ((\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\"))) && !\"done\".equals(data1.get(\"status\"))) {\n" +
//                "                count++;\n" +
//                "            }\n" +
//                "        }\n" +
//                "        if (count > 0) {\n" +
//                "            for (HashMap<String,String> data1 : mapData) {\n" +
//                "                if (\"H\".equals(data1.get(\"status\")) || \"L\".equals(data1.get(\"status\"))) {\n" +
//                "                }\n" +
//                "            }\n" +
//                "        }\n" +
//                "        HashMap<Integer,String> hashMap = new HashMap<>();\n" +
//                "        System.out.println(\"Abnormal count:\" + count);\n" +
//                "        String[] meltFiles = new String[]{\"mp4\",\"mlt2_1\",\"mlt2_2\",\"mlt2_3\",\"mlt2_4\"};\n" +
//                "        if (count >= 1 && count < meltFiles.length) {\n" +
//                "            hashMap.put(count, meltFiles[count]);\n" +
//                "        } else if (count == 0) {\n" +
//                "            hashMap.put(count, meltFiles[count]);\n" +
//                "        } else if (count > 4 && count <= 8) {\n" +
//                "            String largestMlt = meltFiles[meltFiles.length - 1];\n" +
//                "            String abnormalMlt = largestMlt + \" + mlt2_\" + (count - 4);\n" +
//                "            hashMap.put(count, abnormalMlt);\n" +
//                "        } else if (count > 8 && count <= 12) {\n" +
//                "            String largestMlt = meltFiles[meltFiles.length - 1];\n" +
//                "            String abnormalMlt = largestMlt + \" + \" + largestMlt + \" + mlt2_\" + (count - 8);\n" +
//                "            hashMap.put(count, abnormalMlt);\n" +
//                "        } else if (count > 12) {\n" +
//                "            String largestMlt = meltFiles[meltFiles.length - 1];\n" +
//                "            String abnormalMlt = largestMlt + \" + \" + largestMlt + \" + \" + largestMlt + \" + mlt2_\" + (count - 12);\n" +
//                "            hashMap.put(count, abnormalMlt);\n" +
//                "            System.out.println(\"After 12th mlt:\" + hashMap);\n" +
//                "        }\n" +
//                "        HashMap<String, Object> response = new HashMap<>();\n" +
//                "        response.put(\"AbnormalCount\", count);\n" +
//                "        response.put(\"Value\", hashMap.getOrDefault(count, \"UNKNOWN\"));\n" +
//                "        return response;";
//      return condition;
//    }
//
//    @Test
//    public void test3() {
//        String json = getString();
//
//        DecisionMakingTest decisionMakingTest = new DecisionMakingTest();
//        String choosingMlt = decisionMakingTest.data(json);
//        Object getData = Eval.me(choosingMlt);
//        System.out.println("Mlt used: " + getData);
//    }
//}
