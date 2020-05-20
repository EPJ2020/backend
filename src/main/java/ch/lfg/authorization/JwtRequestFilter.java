package ch.lfg.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Provides the Request Filter utilized in SecurityConfig. Stops all requests with invalid tokens before they access the API.
 * copyright: https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/filters/JwtRequestFilter.java
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private AuthorizationService authorizationService;

  @Autowired
  private JwtUtil jwtUtil;

  /**
   * Checks if request has the authorization filter and token in correct form. Extracts username and token from the provided header.
   * Then validity of token is checked on expiration date and username existing in db.
   * If token is valid, function sets the Security Context with username and password from db.
   * @param request the Request coming into the API
   * @param response the Response
   * @param chain next step in the chain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain)
      throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      username = jwtUtil.extractUsername(jwt);
    }


    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails;
      userDetails = this.authorizationService.loadUserByUsername(username);
      if (jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, null);
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }

}
