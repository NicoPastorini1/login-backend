package com.loginbackend.login_jwt.controllers;

import com.loginbackend.login_jwt.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Aquí validas el usuario y la contraseña
        if ("usuario".equals(username) && "contraseña".equals(password)) {
            // Generar JWT si las credenciales son correctas
            return jwtUtils.generateJwt(username);
        }
        return "Credenciales inválidas";
    }
}
