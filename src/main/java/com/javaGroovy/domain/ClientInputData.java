package com.javaGroovy.domain;

import lombok.Data;

import java.util.HashMap;

@Data
public class ClientInputData {
    private String schemaId;
    private String index;
    private HashMap<String, String> metaDataMap;
}
