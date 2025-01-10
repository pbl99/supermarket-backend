package com.palmen.supermarket.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

//Clase para la creación de los tokens 
@Component
public class JwtTokenProvider {
	
	private String jwtSecret = generateSecretKey(); // Generar la clave secreta aleatoria
	private long jwtExpirationDate = 3600000; // 1 hora = 3600000 milisegundos

	// Genera la clave secreta
	private String generateSecretKey() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] key = new byte[32]; // 256 bits
		secureRandom.nextBytes(key);
		return Base64.getEncoder().encodeToString(key);
	}


    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // extract username from JWT token
    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }
}