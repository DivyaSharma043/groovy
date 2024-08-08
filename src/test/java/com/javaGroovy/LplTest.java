package com.javaGroovy;

import com.javaGroovy.domain.ClientInputData;
import com.javaGroovy.domain.ClientRequest;
import com.javaGroovy.domain.ClientRequestData;
import groovy.json.JsonOutput;
import groovy.lang.GroovyShell;
import groovy.util.Eval;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LplTest {

    public static Integer getString() {
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
        String jsonInput = JsonOutput.toJson(clientRequest);

        return executeGroovyScript(jsonInput);
    }

    private static Integer executeGroovyScript(String jsonInput) {
        GroovyShell shell = new GroovyShell();
        String script = """
            import groovy.json.JsonSlurper
            
            def getAbnormalCount(String jsonInput) {
                def slurper = new JsonSlurper()
                def data = slurper.parseText(jsonInput)
                
                List<Map> clientInputDataList = data.clientRequestData.clientInputData
                int count = 0

                clientInputDataList.each { inputData ->
                    def mapData = inputData.metaDataMap
                    String status = mapData.get("status")
                    if (("H".equals(status) || "L".equals(status)) && !"done".equals(status)) {
                        count++
                    }
                }
                if (count == 0) {
                    return 14
                }
                return count
            }
            
            return getAbnormalCount(input)
        """;

        shell.setVariable("input", jsonInput);
        Object result = shell.evaluate(script);
        return (Integer) result;
    }

    public String data(Integer clientRequest) {
        System.out.println("CLIENT DATA: " + clientRequest);
        String condition =
                "def count = new groovy.json.JsonSlurper().parseText('" + clientRequest + "');\n" +
                        " HashMap<String, Object> response = new HashMap<>();\n" +
                        "        String[] meltFiles = new String[]{\"mp4\", \"mlt2_1\", \"mlt2_2\", \"mlt2_3\", \"mlt2_4\"};\n" +
                        "        HashMap<Integer, String> hashMap = new HashMap<>();\n" +
                        "\n" +
                        "        if (count >= 1 && count < meltFiles.length) {\n" +
                        "            hashMap.put(count, meltFiles[count]);\n" +
                        "        } else if (count == 0) {\n" +
                        "            hashMap.put(count, meltFiles[count]);\n" +
                        "        } else if (count > 4 && count <= 8) {\n" +
                        "            String largestMlt = meltFiles[meltFiles.length - 1];\n" +
                        "            String abnormalMlt = largestMlt + \" + mlt2_\" + (count - 4);\n" +
                        "            hashMap.put(count, abnormalMlt);\n" +
                        "        } else if (count > 8 && count <= 12) {\n" +
                        "            String largestMlt = meltFiles[meltFiles.length - 1];\n" +
                        "            String abnormalMlt = largestMlt + \" + \" + largestMlt + \" + mlt2_\" + (count - 8);\n" +
                        "            hashMap.put(count, abnormalMlt);\n" +
                        "        } else if (count > 12) {\n" +
                        "            String largestMlt = meltFiles[meltFiles.length - 1];\n" +
                        "            String abnormalMlt = largestMlt + \" + \" + largestMlt + \" + \" + largestMlt + \" + mlt2_\" + (count - 12);\n" +
                        "            hashMap.put(count, abnormalMlt);\n" +
                        "            System.out.println(\"After 12th mlt: \" + hashMap);\n" +
                        "        }\n" +
                        "\n" +
                        "        response.put(\"AbnormalCount\", count);\n" +
                        "        response.put(\"Value\", hashMap.getOrDefault(count, \"UNKNOWN\"));\n" +
                        "        System.out.println(response);\n" +
                        "        return response;";
        return condition;
    }

    @Test
    public void test1() {
        Integer json = getString();
        LplTest lplTest = new LplTest();
        String choosingMlt = lplTest.data(json);
        Object getData = Eval.me(choosingMlt);
        System.out.println("Mlt used: " + getData);
    }

    @Test
    public void test2(){
        Integer json = getString();
        System.out.println(json);
    }

}
