package com.bookstore.app.security.service;

import com.bookstore.app.entity.User;
import com.bookstore.app.model.*;
import com.bookstore.app.model.user.AuthenticationRequest;
import com.bookstore.app.model.user.AuthenticationResponse;
import com.bookstore.app.model.user.RegisterRequest;
import com.bookstore.app.model.user.Role;
import com.bookstore.app.repository.UserRepository;
import com.bookstore.app.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity register(RegisterRequest request) {
    if (repository.existsByEmail(request.getEmail())) {
      return ResponseEntity.status(409).body(new BaseResponse("error: email already in use"));
    }
    User user = User.builder()
        .firstname(request.getFirstName())
        .lastname(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ROLE_USER)
        .build();
    repository.save(user);
    String jwtToken = jwtService.generateToken(user);
    return ResponseEntity.status(409).body(new BaseResponse("user created successfully"));
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    User user = repository.getByEmail(request.getEmail())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
