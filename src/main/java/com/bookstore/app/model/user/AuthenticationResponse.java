package com.bookstore.app.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AuthenticationResponse {

  private String token;

}
