package com.example.TBDBackend.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.TBDBackend.dtos.LoginDTO;
import com.example.TBDBackend.dtos.LoginResponseDTO;
import com.example.TBDBackend.dtos.RegisterDTO;
import com.example.TBDBackend.models.Client;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.jwt.JwtUtil;
import com.example.TBDBackend.models.WishList;
import com.example.TBDBackend.repositories.ClientRepository;
import com.example.TBDBackend.repositories.WishListRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WishListRepository wishListRepository;

    public Client register(RegisterDTO registerDTO) {
        if(clientRepository.existsByEmail(registerDTO.getEmail())) {
            throw new IllegalStateException("Email already in use");
        }

        WishList wishList = WishList.builder().build();
        WishList savedWishList = wishListRepository.save(wishList);

        Client client = Client.builder()
                .name(registerDTO.getName())
                .address(registerDTO.getAddress())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .phone(registerDTO.getPhone())
                .wishlist(savedWishList)
                .build();

        return clientRepository.save(client);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Optional<Client> possibleClient = clientRepository.findByEmail(loginDTO.getEmail());

        if (possibleClient.isEmpty()) {
            throw new IllegalStateException("Invalid email or password");
        }

        Client client = possibleClient.get();

        if(!client.getPassword().equals(loginDTO.getPassword())) {
            throw new IllegalStateException("Invalid email or password");
        }

        return LoginResponseDTO.builder()
                .token(JwtUtil.createToken(client.getEmail()))
                .clientId(client.getId())
                .build();
    }

    public void verifyToken(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JWT")) {
                    try {
                        String token = cookie.getValue();
                        DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
                        return;
                    } catch (JWTVerificationException e) {
                        throw new IllegalStateException("Invalid JWT");
                    }
                }
            }
            throw new IllegalStateException("JWT not found");
        }
        throw new IllegalStateException("Cookies not found");
    }

    public Client getClientByToken(Cookie[] cookies) {
        String token = JwtUtil.getJwtFromCookies(cookies);
        String email = JwtUtil.extractEmailFromJwt(token);
        Optional<Client> client = clientRepository.findByEmail(email);

        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }

        return client.get();
    }

    public Client getAuthClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            Optional<Client> clientEntity = clientRepository.findByEmail(email);

            if (clientEntity.isEmpty()) {
                throw new EntityNotFoundException("Client not found");
            }
            return clientEntity.get();
        }
        throw new EntityNotFoundException("No authenticated client found");
    }
}
