package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ru
 * @create 2020-11-10-15:42
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping(value = "/hello")
    public Map<String,Object> hello(){
        Map<String,Object> map=new HashMap<>();
        map.put("result","0");
        return map;
    }
}
