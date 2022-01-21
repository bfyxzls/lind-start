package javaagent.demo;

import java.lang.instrument.Instrumentation;

public class PreMainAgent {
  public static void premain(String param, Instrumentation instrumentation) {
    System.out.println("大家好，我是agent");
    System.out.println("agent param:" + param);
  }
}
