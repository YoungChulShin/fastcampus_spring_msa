package dev.practice.gift.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

public final class TokenGenerator {

  private static final int TOKEN_LENGTH = 20;

  public static String randomCharacter(int length) {
    return RandomStringUtils.randomAlphabetic(length);
  }

  public static String randomCharacterWithPrefix(String prefix) {
    return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
  }
}
