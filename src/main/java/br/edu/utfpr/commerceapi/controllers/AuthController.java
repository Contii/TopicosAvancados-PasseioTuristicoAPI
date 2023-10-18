package br.edu.utfpr.commerceapi.controllers;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.dto.AuthDTO;
import br.edu.utfpr.commerceapi.dto.Message;
import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.security.JwtUtil;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt_secret}")
    private String jwtSecret;

    @Value("${jwt_expires}")
    private Long jwtExpiresDefault = 3600L;

    @PostMapping
    public ResponseEntity<Object> loginHandler(@Valid @RequestBody AuthDTO body,
            @RequestHeader(value = "expires-in", required = false) Long jwtExpires) {

        jwtExpires = Objects.isNull(jwtExpires) ? jwtExpiresDefault : jwtExpires;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            // Dados do usuário autenticado
            var user = (Person) auth.getPrincipal();

            // Carga (dados) para incluir no Token
            var claims = new HashMap<String, Object>();
            claims.put("id", user.getId().toString());
            claims.put("username", body.getUsername());

            // var now1 = LocalDateTime.now(ZoneId.of("UTC"));
            var now = Instant.now().atZone(ZoneId.of("UTC")).withNano(0);

            // Gerar o token JWT
            String token = jwtUtil.generateToken(claims, jwtSecret, jwtExpires);

            var res = new HashMap<String, Object>();
            res.put("jwt-token", token);
            res.put("user", user);
            res.put("issuedIn", now);
            res.put("expiresIn", now.plus(jwtExpires, ChronoUnit.SECONDS));

            return ResponseEntity.ok(res);
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.b("Invalid credentials"));
        }
    }

}