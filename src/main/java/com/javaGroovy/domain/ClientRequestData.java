package com.javaGroovy.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ClientRequestData {

    private String schemaId;
    private List<HashMap<String, String>> metaDataMap;
}
