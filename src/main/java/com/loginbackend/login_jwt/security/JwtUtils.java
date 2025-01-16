package com.loginbackend.login_jwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Método para generar el JWT
    public String generateJwt(String username) {
        return Jwts.builder()
                .setSubject(username) // Nombre de usuario como subject
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Fecha de expiración
                .signWith(SignatureAlgorithm.HS256, secretKey) // Firmar con el algoritmo y la clave secreta
                .compact(); // Crear el JWT compactado
    }

    // Método para validar el JWT
    public String validateJwt(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey) // Configurar la clave secreta
                    .parseClaimsJws(jwt) // Parsear el JWT
                    .getBody()
                    .getSubject(); // Obtener el "subject" del JWT (username)
        } catch (Exception e) {
            return null; // Si el token no es válido o ha expirado, retornar null
        }
    }
}
