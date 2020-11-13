package com.lind.start.test.controller;

import com.lind.start.test.kafka.MessageSender;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    MessageSender sender;

    @GetMapping("/kafka")
    public String index() {
        sender.send("hello world kafka!" + DateTime.now());
        return "success";
    }

}
