package com.web.ecommerceapp.service.impl;

import com.web.ecommerceapp.entity.RefreshToken;
import com.web.ecommerceapp.entity.User;
import com.web.ecommerceapp.exception.TokenRefreshException;
import com.web.ecommerceapp.repository.RefreshTokenRepository;
import com.web.ecommerceapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Slf4j
@Service
public class RefreshTokenService {
    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken getByToken(String token) {
        log.info(token);
        return refreshTokenRepository.findByToken(token).orElseThrow(()->new TokenRefreshException(token, "Refresh token is not in database"));
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.getUserByEmailOrUsername(username,username));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken generateNewRefreshToken(User user) {
        RefreshToken refreshToken=refreshTokenRepository.getByUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        log.info(token.getToken());
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
