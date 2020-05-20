package ch.lfg.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class provides all functionality of token handling.
 * Copyright: https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
 */

@Service
public class JwtUtil {
  private String secret = "11bf013435de13452b984e4607222dfaefe8484f8d6bdd2e43f17e0d5e5e198d8d06f5d39fa475e9909fb4951584cb37af21919a46190f0f30833c69a0f6a7e0";

  /**
   * Extracts username from token via function provided by jsonwebtoken library
   * @return string of username in token
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Extracts date from token via function provided by jsonwebtoken library
   * @return date saved in token
   */
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**
   * Returns in parameter specified part of the token, for example username or expiration date.
   * @param claimsResolver defines function for which part of the claim is to be returned
   * @return the extracted part of the claim
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  /**
   * Checks if token still valid by comparing expiration date of token with date of now.
   * @param token provided by request
   * @return true if token still valid
   */
  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   * @param userLogin login details as UserDetails class. Username is used for token creation.
   * @return token as string
   */
  public String generateToken(UserDetails userLogin) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userLogin.getUsername());
  }

  /**
   * Creates Token. Sets expiration date currently to 10 days. Will be changed in production.
   * @param claims we dont handle claims other than username in this project
   * @param subject sets userlogin details of token, here username
   * @return String token
   */
  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, secret).compact();
  }

  /**
   * Checks if token not expired and provided username is same as username enbedded in token.
   * @param token token from request
   * @param userLogin userlogin provided to check against
   * @return boolean true if client can access API
   */
  public Boolean validateToken(String token, UserDetails userLogin) {
    final String username = extractUsername(token);
    return (username.equals(userLogin.getUsername()) && !isTokenExpired(token));
  }

}
