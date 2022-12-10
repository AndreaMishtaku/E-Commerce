package com.web.ecommerceapp.payload.user;
import lombok.Data;
import lombok.Getter;

@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;


    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}


