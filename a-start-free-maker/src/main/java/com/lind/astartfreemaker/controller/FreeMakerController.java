package com.lind.start.test.controller;

import com.lind.start.test.dto.UserDTO;
import org.apache.hadoop.hbase.shaded.org.apache.commons.collections.ArrayStack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FreeMakerController {
    @GetMapping("/free-marker")
    public String index(){
        return "index";
    }

    @RequestMapping("/user-list")
    public String selectUser(ModelMap map){
        List<UserDTO> userList =new ArrayStack();
        userList.add(UserDTO.builder().name("lind").email("zls@sina.com").build());
        userList.add(UserDTO.builder().name("zzl").email("bfyxzls@sina.com").build());
        map.put("userList",userList);
        return "userlist";
    }

}
