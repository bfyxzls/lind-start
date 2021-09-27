package com.lind.lindmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.lindmanager.vo.UserVo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

  @SneakyThrows
  @GetMapping("user/list")
  public String list(Model model) {
    List<UserVo> userVoList = new ArrayList<>();
    userVoList.add(UserVo.builder().name("zzl").address("beijing").date(Date.from(Instant.now())).build());
    userVoList.add(UserVo.builder().name("zhz").address("beijing").date(Date.from(Instant.now())).build());
    userVoList.add(UserVo.builder().name("zql").address("hebei").date(Date.from(Instant.now())).build());
    model.addAttribute("list", new ObjectMapper().writeValueAsString(userVoList));
    return "user/list";
  }
}
