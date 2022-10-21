package com.authenticate.FoodOrdering.dto.response;

import lombok.Data;

@Data
public class Response {

    private String resp_Code;
    private String resp_Msg;

    public Response() {
    }

    public Response(String resp_Code, String resp_Msg) {
        this.resp_Code = resp_Code;
        this.resp_Msg = resp_Msg;
    }

    private String token;
    private Object data;
}
