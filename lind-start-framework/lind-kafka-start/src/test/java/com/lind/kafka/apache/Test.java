package com.lind.kafka.apache;

public class Test {
    @org.junit.Test
    public void publish() {
        Producer.publishEvent("topic001", "hello");
    }
}
