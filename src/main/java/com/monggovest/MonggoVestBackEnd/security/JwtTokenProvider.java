package com.monggovest.MonggoVestBackEnd.security;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.monggovest.MonggoVestBackEnd.security.SecurityConstant.EXPIRATION_TIME;
import static com.monggovest.MonggoVestBackEnd.security.SecurityConstant.SECRET;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        UserModel user = (UserModel) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+ EXPIRATION_TIME);

        String userId = Long.toString(user.getUserId());

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", (Long.toString(user.getUserId())));
        claims.put("userEmail", user.getUserEmail());
        claims.put("userFullName", user.getUserFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex){
            System.out.println("Invalid Jwt Signature");
        } catch (MalformedJwtException ex){
            System.out.println("Invalid Jwt Token");
        } catch (ExpiredJwtException ex){
            System.out.println("expired Jwt Token");
        } catch (UnsupportedJwtException ex){
            System.out.println("unsupported Jwt Token");
        } catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String userId = (String)claims.get("userId");

        return Long.parseLong(userId);
    }
}
