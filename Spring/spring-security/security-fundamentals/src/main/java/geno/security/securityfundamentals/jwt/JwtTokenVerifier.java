package geno.security.securityfundamentals.jwt;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                                                                                throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            //Jwts.parserBuilder().build().se
            Jws<Claims> claims = Jwts.parser()
                    //.setSigningKey(Keys.hmacShaKeyFor("sec5n74m864n34v5y7648b4769m57ure".getBytes()))
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claims.getBody();
            String subject = body.getSubject(); //username
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(
                new Function<Map<String, String>, SimpleGrantedAuthority>() {
                    @Override
                    public SimpleGrantedAuthority apply(@Nullable Map<String, String> stringStringMap) {
                        return new SimpleGrantedAuthority(stringStringMap.get("authority"));
                    }
                }
            ).collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                subject,
                null,
                simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new JwtException(e.getMessage());
        }

        chain.doFilter(request, response);
    }
}
