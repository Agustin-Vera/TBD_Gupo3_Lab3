package com.backend.TBD_Backend.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.TBD_Backend.dtos.LoginDTO;
import com.backend.TBD_Backend.dtos.LoginResponseDTO;
import com.backend.TBD_Backend.dtos.RegisterDTO;
import com.backend.TBD_Backend.exception.EntityNotFoundException;
import com.backend.TBD_Backend.jwt.JwtUtil;
import com.backend.TBD_Backend.models.ClientEntity;
import com.backend.TBD_Backend.repositories.ClientRepository;
import jakarta.servlet.http.Cookie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity register(RegisterDTO clientDTO) {
        ClientEntity existingClientEntityByEmail = clientRepository.findByEmail(clientDTO.getEmail());
        if (existingClientEntityByEmail != null) {
            throw new IllegalStateException("The email is already used");
        }

        ClientEntity client = new ClientEntity(clientDTO.getName(), clientDTO.getAddress(), clientDTO.getEmail(), clientDTO.getPassword(), clientDTO.getPhone());

        return clientRepository.save(client);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        ClientEntity clientEntity = clientRepository.findByEmail(loginDTO.getEmail());
        if (clientEntity == null) {
            throw new IllegalStateException("The email or password is incorrect");
        }
        if (!clientEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new IllegalStateException("The email or password is incorrect");
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(JwtUtil.createToken(loginDTO.getEmail()), clientEntity.getId());

        return loginResponseDTO;
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

    public ClientEntity getClientByToken(Cookie[] cookies) {
        String token = JwtUtil.getJwtFromCookies(cookies);
        String email = JwtUtil.extractEmailFromJwt(token);
        ClientEntity clientEntity = clientRepository.findByEmail(email);

        if (clientEntity == null) {
            throw new EntityNotFoundException("Client not found");
        }

        return clientEntity;
    }

    public ObjectId getAuthIdClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            ClientEntity clientEntity = clientRepository.findByEmail(email);

            if (clientEntity == null) {
                throw new EntityNotFoundException("Client not found");
            }
            return clientEntity.getId();
        }
        throw new EntityNotFoundException("No authenticated client found");
    }
}

