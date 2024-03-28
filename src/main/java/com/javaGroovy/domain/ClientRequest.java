package com.javaGroovy.domain;

import lombok.Data;

@Data
public class ClientRequest {

    private String requestUuid;
    private String useCaseId;
    private ClientRequestData clientRequestData;

}
