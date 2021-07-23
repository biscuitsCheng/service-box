package com.biscuits.core.framework.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class PackageScanner extends ClassPathBeanDefinitionScanner {

    private Logger log = LoggerFactory.getLogger(PackageScanner.class);

    public PackageScanner(BeanDefinitionRegistry registry, Environment environment) {
        super(registry, true, environment);
        this.registerDefaultFilters();
        this.addExcludeFilter(new AnnotationTypeFilter(SpringBootApplication.class));
    }


    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            log.info("自动加载bean路径{}", basePackage);

        }
        return super.doScan(basePackages);
    }


    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition);
    }

    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
        return super.checkCandidate(beanName, beanDefinition);
    }

}
