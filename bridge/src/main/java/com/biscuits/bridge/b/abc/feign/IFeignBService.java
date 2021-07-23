package com.biscuits.bridge.b.abc.feign;

import com.biscuits.bridge.b.abc.IBService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("b")
public interface IFeignBService extends IBService {
    @RequestMapping("/feign/work")
    String work();
}
