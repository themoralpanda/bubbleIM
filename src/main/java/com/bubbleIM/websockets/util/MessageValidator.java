package com.bubbleIM.websockets.util;

public interface MessageValidator {

  void validate(String message) throws InvalidMessageException;
}
