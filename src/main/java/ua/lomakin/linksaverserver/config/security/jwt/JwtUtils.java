package ua.lomakin.linksaverserver.config.security.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.lomakin.linksaverserver.config.security.UserDetailsImpl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private String jwtSecret = "ajsdfkjsadfhjksadfogkdjkdfgjdfgjsdfgdfdfhkjsadhflkjashdflkjhasldkjfhajghlkjbklcxzvue";
    private int jwtExpirationMs = 86400000;
    private String jwtIssuser = "LinkServer";

    private Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        Map<String, Object> data = new HashMap<>();
        data.put("userid", userPrincipal.getId());
        data.put("username", userPrincipal.getUsername());

        return Jwts.builder()
                .setIssuer(jwtIssuser)
                .setIssuedAt(
                        Date.from(
                                LocalDateTime.now()
                                        .atZone(ZoneOffset.systemDefault())
                                        .toInstant()
                        )
                )
                .setExpiration(
                        Date.from(
                                LocalDateTime.now()
                                        .plus(jwtExpirationMs, ChronoUnit.MILLIS)
                                        .atZone(ZoneOffset.systemDefault())
                                        .toInstant()
                        )
                )
                .addClaims(data)
                .signWith(key)
                .compact();
    }

    private JwtParser JWT_PARSER = Jwts.parserBuilder()
            .requireIssuer(jwtIssuser)
            .setSigningKey(key)
            .build();

    public String getUserNameFromJwtToken(String token) {
        return  (String) JWT_PARSER.parseClaimsJws(token).getBody().get("username");
    }

    public Long getUserIdFromJwtToken(String token) {
        return  Long.valueOf(String.valueOf(JWT_PARSER.parseClaimsJws(token).getBody().get("userid")));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            JWT_PARSER.parseClaimsJws(authToken).getBody();
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


}
