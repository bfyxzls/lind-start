package com.pkulaw.schedule.controller;

import com.pkulaw.schedule.dto.JobDto;
import com.pkulaw.schedule.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("quartz-view")
public class ViewController {
    @Autowired
    QuartzService quartzService;

    @GetMapping("add")
    public String add() {
        return "view/add";
    }

    @GetMapping("list")
    public String list(Model model) {
        List<JobDto> list = quartzService.getAllJob();
        model.addAttribute("result", list);
        return "view/list";
    }
}
