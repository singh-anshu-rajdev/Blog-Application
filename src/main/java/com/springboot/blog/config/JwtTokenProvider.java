package com.springboot.blog.config;

import com.springboot.blog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-millisecond}")
    private long jwtExpirationDate;

    //Generate jwt token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expiredDate).signWith(key()).compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        return username;
    }

    //validate jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        }catch (MalformedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
        }catch (ExpiredJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Expired JWT token");
        }catch (UnsupportedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Jwt claims String is empty");
        }

    }
}
