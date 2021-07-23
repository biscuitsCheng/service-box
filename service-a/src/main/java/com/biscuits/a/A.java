package com.biscuits.a;

import com.biscuits.core.framework.container.AbstractContainer;


public class A extends AbstractContainer {
    private static A a = new A();


    public static A getInstance() {
        return a;
    }

    private A() {
    }

}
