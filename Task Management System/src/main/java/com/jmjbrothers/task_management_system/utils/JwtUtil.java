package com.jmjbrothers.task_management_system.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {


    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);

        private final String SECRET = "daa3e554bfe0564a639398334720018ab198404dff87043704bb52611a0e74a7";

        public String createToken (String email){
            Map<String, Object> claims = new HashMap<>();
            return generateToken(claims, email);

        }


        private String generateToken (Map < String, Object > extraClaims, String email){

            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        private Key getSigningKey () {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public boolean validToken (String token, UserDetails userDetails){
            final String userName = extractUserName(token);
            return (userName.equals(userDetails.getUsername())) && isTokenExpired(token);
        }

        private boolean isTokenExpired (String token){
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration (String token){
            return extractClaim(token, Claims::getExpiration);
        }

        public String extractUserName (String token){
            return extractClaim(token, Claims::getSubject);
        }

        private <T > T extractClaim(String token, Function < Claims, T > claimsResolvers) {
            final Claims claims = extractAllClaims(token);
            return claimsResolvers.apply(claims);
        }

        private Claims extractAllClaims (String token){
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

    }
}
