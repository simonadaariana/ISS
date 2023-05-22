package com.example.proiect.service;

import com.example.proiect.dbo.AuthResponse;
import com.example.proiect.dbo.LoginRequest;
import com.example.proiect.dbo.RegisterRequest;
import com.example.proiect.dbo.RegisterResponse;
import com.example.proiect.model.User;
import com.example.proiect.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse signup(RegisterRequest registerRequest) {
        if (!registerRequest.getAuthToken().isBlank() || !registerRequest.getAuthToken().isEmpty()) {
            Optional<User> user = userRepository.findUsersByEmail(registerRequest.getEmail());
            if (user.isEmpty()) {
                User newUser = new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getRole());
                userRepository.save(newUser);
                return new RegisterResponse("Tutto bene", true);
            }
        }
        return new RegisterResponse("Not bueno my friend", false);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        GrantedAuthority role = roles.iterator().next();
        String token = Jwts.builder()
                .setId(loginRequest.getEmail())
                .setIssuedAt(new Date())
                .setSubject(loginRequest.getEmail())
                .claim("role", role.getAuthority())
                .setIssuer("bugreport")
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                ).compact();

        return new AuthResponse(token, loginRequest.getEmail());
    }
}
