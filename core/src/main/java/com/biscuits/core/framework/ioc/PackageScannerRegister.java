package com.biscuits.core.framework.ioc;

import com.biscuits.core.framework.ServerNode;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Set;


public class PackageScannerRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    private ResourceLoader resourceLoader;
    private Environment environment;

    public PackageScannerRegister() {
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registerBeanDefinitions(registry);
    }

    public void registerBeanDefinitions(BeanDefinitionRegistry registry) {
        PackageScanner scanner = new PackageScanner(registry, environment);
        if (this.resourceLoader != null) {
            scanner.setResourceLoader(this.resourceLoader);
        }
        Set<String> basePackages = ServerNode.getContainer().getBasePackages();
        if (basePackages == null || basePackages.isEmpty()) {
            return;
        }
        scanner.doScan(StringUtils.toStringArray(basePackages));
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
