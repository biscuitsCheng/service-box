package com.biscuits.core.framework.container;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Lifecycle {
    /**
     * 返回需要加载的package
     *
     * @return
     */
    default Set<String> init() {
        HashSet<String> basePackages = new HashSet<>();
        basePackages.add(this.getClass().getPackage().getName());
        return basePackages;
    }


    void start(List<ContainerConfig> configs, ConfigurableApplicationContext context);

    void destroy();
}
