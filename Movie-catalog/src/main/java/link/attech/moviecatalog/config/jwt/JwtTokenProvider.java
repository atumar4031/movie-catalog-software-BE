package link.attech.moviecatalog.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-time}")
    private Long jwtExpirationTime;

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        String username = authentication.getName();
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationTime);

        String access_token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return access_token;
    }
    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
            try{
                log.info("token is: {}", token);
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                return true;
            }catch (MalformedJwtException exception){
                throw new RuntimeException("Invalid JWT_Token");
            }
    }
}
