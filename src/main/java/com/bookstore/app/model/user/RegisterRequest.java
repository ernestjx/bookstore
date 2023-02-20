package com.bookstore.app.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank(message = "firstName is mandatory")
  private String firstName;
  @NotBlank(message = "lastName is mandatory")
  private String lastName;
  @NotBlank(message = "email is mandatory")
  private String email;
  @NotBlank(message = "password is mandatory")
  private String password;
}
