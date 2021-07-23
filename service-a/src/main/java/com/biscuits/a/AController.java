package com.biscuits.a;

import com.biscuits.bridge.b.abc.IBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class AController {

    private IBService bService;

    @GetMapping("/index")
    public String index() {
        return bService.work();
    }

    @Autowired
    public void setBService(IBService bService) {
        this.bService = bService;
    }
}
