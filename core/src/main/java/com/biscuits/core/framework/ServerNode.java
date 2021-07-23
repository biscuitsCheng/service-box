package com.biscuits.core.framework;


import com.biscuits.core.framework.container.Container;

/**
 * 管理当前是那个模块启动的
 */
public class ServerNode {
    private final static ServerNode instance;

    private Container container;

    static {
        instance = new ServerNode();
    }


    public static void setContainer(Container container) {
        getInstance().container = container;
    }

    public static ServerNode getInstance() {
        return instance;
    }

    public static Container getContainer() {
        return getInstance().container;
    }

}
