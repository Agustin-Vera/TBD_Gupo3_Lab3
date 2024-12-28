package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.LoginDTO;
import com.example.TBDBackend.dtos.LoginResponseDTO;
import com.example.TBDBackend.dtos.RegisterDTO;
import com.example.TBDBackend.entities.Client;
import com.example.TBDBackend.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<Client> register(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        LoginResponseDTO responseDTO = authService.login(loginDTO);

        String cookieValue = "JWT=" + responseDTO.getToken() + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=" + (60 * 60 * 24);

        response.addHeader("Set-Cookie", cookieValue);

        HashMap<String, String> message = new HashMap<>();
        message.put("user_id", responseDTO.getClientId());
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
    public ResponseEntity<Client> getClientByJwt(HttpServletRequest request) {
        return new ResponseEntity<>(authService.getClientByToken(request.getCookies()), HttpStatus.OK);
    }
}
