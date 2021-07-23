package com.biscuits.b;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class BFeignController {
    @GetMapping("/work")
    public String index() {
        return "远程调用";
    }
}
