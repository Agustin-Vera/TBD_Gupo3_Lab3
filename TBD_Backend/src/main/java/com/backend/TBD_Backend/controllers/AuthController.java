package com.backend.TBD_Backend.controllers;

import com.backend.TBD_Backend.dtos.LoginDTO;
import com.backend.TBD_Backend.dtos.LoginResponseDTO;
import com.backend.TBD_Backend.dtos.RegisterDTO;
import com.backend.TBD_Backend.models.ClientEntity;
import com.backend.TBD_Backend.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ClientEntity> register(@RequestBody RegisterDTO clientDTO) {
        return new ResponseEntity<>(authService.register(clientDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, ObjectId>> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        LoginResponseDTO responseDTO = authService.login(loginDTO);

        String cookieValue = "JWT=" + responseDTO.getToken() + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=" + (60 * 60 * 24);

        response.addHeader("Set-Cookie", cookieValue);

        HashMap<String, ObjectId> message = new HashMap<>();
        message.put("user_id", responseDTO.getUserId());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Boolean>> login(HttpServletResponse response){
        Cookie jwtCookie = new Cookie("JWT", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);

        response.addCookie(jwtCookie);

        HashMap<String, Boolean> message = new HashMap<>();
        message.put("success", true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<Map<String, Boolean>> verifyToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        authService.verifyToken(cookies);
        HashMap<String, Boolean> message = new HashMap<>();
        message.put("success", true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<ClientEntity> getClientByJwt(HttpServletRequest request) {
        return new ResponseEntity<>(authService.getClientByToken(request.getCookies()), HttpStatus.OK);
    }
}

