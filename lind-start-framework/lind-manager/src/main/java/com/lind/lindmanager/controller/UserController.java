package com.lind.lindmanager.controller;

import com.lind.lindmanager.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping("list")
    public String list(Model model) {
        List<UserVo> userVoList = new ArrayList<>();
        userVoList.add(UserVo.builder().name("zzl").age(38).address("beijing").date(Date.from(Instant.now())).build());
        userVoList.add(UserVo.builder().name("zhz").age(12).address("beijing").date(Date.from(Instant.now())).build());
        userVoList.add(UserVo.builder().name("zql").age(41).address("hebei").date(Date.from(Instant.now())).build());
        model.addAttribute("list", userVoList);
        return "user/list";
    }
}
