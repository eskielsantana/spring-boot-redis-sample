package com.ezequiel.redsample.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SwaggerController
{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }
}