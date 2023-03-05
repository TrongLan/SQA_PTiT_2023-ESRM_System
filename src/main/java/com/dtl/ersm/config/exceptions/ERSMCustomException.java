package com.dtl.ersm.config.exceptions;

import lombok.Getter;

@Getter
public class ERSMCustomException extends Exception {
  private final String customErrorMessage;

  public ERSMCustomException(String customErrorMessage) {
    this.customErrorMessage = customErrorMessage;
  }
}
