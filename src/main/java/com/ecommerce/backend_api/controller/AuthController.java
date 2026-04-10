package com.ecommerce.backend_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend_api.dtos.UserDTO;
import com.ecommerce.backend_api.service.LoginService;
import com.ecommerce.backend_api.service.UserService;

@RestController 
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/registro")
    public ResponseEntity<String> register(@RequestBody UserDTO request) {
        try {
            userService.registerUser(request.getEmail(), request.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO dto){
        try {
            String token = loginService.login(dto.getEmail(),dto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(token);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
}
