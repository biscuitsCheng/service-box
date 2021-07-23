package com.biscuits.b;

import com.biscuits.bridge.b.abc.IBService;
import org.springframework.stereotype.Service;

@Service
public class BServiceImpl implements IBService {
    @Override
    public String work() {
        return "本地调用";
    }
}
