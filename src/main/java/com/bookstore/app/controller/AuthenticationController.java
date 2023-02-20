package com.bookstore.app.controller;

import com.bookstore.app.model.user.AuthenticationRequest;
import com.bookstore.app.model.user.AuthenticationResponse;
import com.bookstore.app.model.BaseResponse;
import com.bookstore.app.model.user.RegisterRequest;
import com.bookstore.app.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<BaseResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return service.register(request);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
