package com.javaGroovy.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ClientRequestData {
    private List<ClientInputData> clientInputData;
}
