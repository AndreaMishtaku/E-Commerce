package com.web.ecommerceapp.payload.response;

import lombok.Getter;

@Getter
public class ActionSuccessful {
    boolean result;
    private String message;

    public ActionSuccessful(boolean result, String message) {
        this.result = result;
        this.message = message;
    }
}
