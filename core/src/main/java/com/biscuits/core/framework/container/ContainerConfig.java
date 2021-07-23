package com.biscuits.core.framework.container;

public class ContainerConfig {
    /**
     * 容器名
     */
    private String name;
    /**
     * 运行模式
     * @see RunModel
     */
    private String model;
    /**
     * 容器类
     */
    private String containerClass;
    /**
     * 本容器类对外暴露的远程调用的package地址
     */
    private String openFeignPackage;

    public String getOpenFeignPackage() {
        return openFeignPackage;
    }

    public void setOpenFeignPackage(String openFeignPackage) {
        this.openFeignPackage = openFeignPackage;
    }

    public String getContainerClass() {
        return containerClass;
    }

    public void setContainerClass(String containerClass) {
        this.containerClass = containerClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public RunModel getRunModel() {
        return RunModel.getRunModel(model);
    }
}
