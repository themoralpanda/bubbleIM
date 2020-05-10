package com.bubbleIM;

public final class AppLauncher {

  private AppLauncher() {
  }

  public static void main(String args[]) {
    Boot app = new Boot();
    app.startup();
  }
}
