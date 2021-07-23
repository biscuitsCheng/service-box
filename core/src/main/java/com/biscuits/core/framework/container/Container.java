package com.biscuits.core.framework.container;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.Set;


public interface Container extends Lifecycle {

    Set<String> getBasePackages();

    void initBySelf();

    void runBySelf(ConfigurableApplicationContext configurableApplicationContext);

    Set<String> getOpenFeignPackages();

}
