package com.lind.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.common.locale.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
//@EnableJacksonFormatting
public class CommonApplication {


    @Autowired
    ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    public String print(){
        System.out.println("print class loader1.1");
        return "print class loader1.1";
    }
    @GetMapping("version")
    public ResponseEntity version() {
        return ResponseEntity.ok("1.0.0");
    }

    @GetMapping("m")
    public String m() {
        return LocaleMessageUtils.getMessage("title");
    }

    @GetMapping("j")
    public ResponseEntity j() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("date", LocalDate.now());
   //     String result = objectMapper.writeValueAsString(map);
        return ResponseEntity.ok(map);
    }

}
