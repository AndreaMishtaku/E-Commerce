package com.web.ecommerceapp.security;

import com.web.ecommerceapp.exception.AppException;
import com.web.ecommerceapp.payload.user.TokenDto;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public TokenDto generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + this.jwtExpirationInMs);
        Date refreshTokenExp = new Date((expiredDate.getTime() - 1000) + 2L * this.jwtExpirationInMs);

        String accessToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();


        String refreshToken = Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(refreshTokenExp)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();

        return new TokenDto(accessToken,refreshToken);
    }

    public String getUsernameFromJWT(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token)
    {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException signatureException) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException malformedJwtException) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new AppException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }

    }
}
