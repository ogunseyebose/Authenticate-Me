package com.authenticate.AuthenticateMe.dto.response;

import lombok.Data;

@Data
public class Response {

    private String resp_Code;
    private String resp_Msg;
    private String token;
    private Object data;
}
