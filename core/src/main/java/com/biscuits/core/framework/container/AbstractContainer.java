package com.biscuits.core.framework.container;

import com.biscuits.core.framework.JsonUtils;
import com.biscuits.core.framework.ServerNode;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.*;

public abstract class AbstractContainer implements Container {
    private Logger log = LoggerFactory.getLogger(AbstractContainer.class);

    protected static ConfigurableApplicationContext context;
    /**
     * 容器直接扫描注册进ioc的package
     */
    protected Set<String> basePackages;
    /**
     * 容器远程调用的feign接口package（都在bridge模块中）
     */
    protected Set<String> openFeignPackages;


    protected Map<String, Container> containerMap;
    protected List<ContainerConfig> cloudContainer;

    public AbstractContainer() {
        this.basePackages = new HashSet<>();
        this.basePackages.add(this.getClass().getPackage().getName());
        this.openFeignPackages = new HashSet<>();
        this.containerMap = new HashMap<>();
        this.cloudContainer = new ArrayList<>();
    }

    @Override
    public void start(List<ContainerConfig> configs, ConfigurableApplicationContext context) {
        for (Container container : containerMap.values()) {
            container.start(configs, context);
        }
    }

    public void addBasePackages(Set<String> basePackages) {
        if (basePackages == null) {
            return;
        }
        this.basePackages.addAll(basePackages);
    }

    public void addBasePackage(String basePackage) {
        if (basePackage == null) {
            return;
        }
        this.basePackages.add(basePackage);
    }

    public void addOpenFeignPackages(Set<String> basePackages) {
        if (basePackages == null) {
            return;
        }
        this.basePackages.addAll(basePackages);
    }

    public void addOpenFeignPackage(String openFeignPackage) {
        if (openFeignPackage == null) {
            return;
        }
        this.openFeignPackages.add(openFeignPackage);
    }

    @Override
    public void initBySelf() {
        ServerNode.setContainer(this);
        List<ContainerConfig> configs = getConfigs();
        if (configs == null) {
            return;
        }
        try {
            for (ContainerConfig config : configs) {
                RunModel runModel = config.getRunModel();
                if (RunModel.LOCAL.equals(runModel)) {
                    String name = config.getName();
                    Class<Container> clz = (Class<Container>) Class.forName(config.getContainerClass());
                    Container container = (Container) clz.getDeclaredMethod("getInstance").invoke(null);
                    containerMap.put(name, container);
                    addBasePackages(container.init());
                } else {
                    if (cloudContainer == null) {
                        cloudContainer = new ArrayList<>();
                    }
                    addOpenFeignPackage(config.getOpenFeignPackage());
                    cloudContainer.add(config);
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("找不到对应的类", e);
            throw new RuntimeException("找不到对应的类");
        } catch (NoSuchMethodException e) {
            log.error("找不到对应的方法", e);
            throw new RuntimeException("找不到对应的方法");
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error("执行方法失败", e);
            throw new RuntimeException("执行方法失败");
        }
    }

    private static List<ContainerConfig> getConfigs() {
        ClassPathResource classPathResource = new ClassPathResource("container.json");
        if (!classPathResource.exists()) {
            return null;
        }
        try (InputStream inputStream = classPathResource.getInputStream()) {
            String jsonStr = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
            return JsonUtils.fromJson(jsonStr, new TypeToken<List<ContainerConfig>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException("解析容器配置失败");
        }
    }

    @Override
    public void destroy() {
        for (Container container : containerMap.values()) {
            container.destroy();
        }
    }

    @Override
    public void runBySelf(ConfigurableApplicationContext configurableApplicationContext) {
        context = configurableApplicationContext;
        start(cloudContainer, context);
    }

    public Set<String> getBasePackages() {
        return basePackages;
    }

    @Override
    public Set<String> getOpenFeignPackages() {
        return openFeignPackages;
    }

}
