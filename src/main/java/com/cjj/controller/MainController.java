package com.cjj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cjj
 * @date 2020/7/17
 * @description
 */
@Controller
@RequestMapping("main")
public class MainController {
    @RequestMapping("navbar")
    public String navbar(){
        return "/common/navbar";
    }

    @RequestMapping("sidebar")
    public String sidebar(){
        return "/common/sidebar";
    }
}
