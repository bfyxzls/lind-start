package com.lind.lindmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("free")
public class F1Controller {

  @RequestMapping("list")
  public String selectUser(Model model) {
    model.addAttribute("forgetPasswordAddress", "ok");
    return "f1/list";
  }

  @PostMapping(value = "add")
  public ResponseEntity selectUser(@RequestParam Map<String, Object> map) {
    return ResponseEntity.ok(map);
  }

}
