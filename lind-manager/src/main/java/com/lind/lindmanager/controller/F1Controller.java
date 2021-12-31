package com.lind.lindmanager.controller;

import com.lind.common.util.FreeMarkerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("f1")
public class F1Controller {

  @RequestMapping("list")
  public String selectUser(Model model) {
    model.addAttribute("forgetPasswordAddress", "ok");
    return "f1/list";
  }

  @RequestMapping("error")
  public void error(HttpServletResponse response, @RequestParam String version) throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("message", "程序出错");
    String html = new FreeMarkerUtil().processTemplate(map, "error/error.ftl", version);

    response.setContentType("text/html;charset=UTF-8");
    response.getWriter().write(html);
  }

  @PostMapping(value = "add")
  public ResponseEntity selectUser(@RequestParam Map<String, Object> map) {
    return ResponseEntity.ok(map);
  }

}
