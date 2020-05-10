package com.bubbleIM.websockets.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

public class MessageValidatorImpl implements MessageValidator {

  @Inject
  private ObjectMapper objectMapper;

  @Override
  public void validate(String message) throws InvalidMessageException {

  }
}
