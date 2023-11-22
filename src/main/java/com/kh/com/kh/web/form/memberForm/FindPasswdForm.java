package com.kh.com.kh.web.form.memberForm;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data

public class FindPasswdForm {

  @NotBlank

  @Email(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")

  private String email;

  @NotBlank

  @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$")

  private String tel;

}