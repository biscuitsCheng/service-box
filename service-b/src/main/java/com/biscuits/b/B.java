package com.biscuits.b;

import com.biscuits.core.framework.container.AbstractContainer;

public class B extends AbstractContainer {
    private static B b = new B();


    public static B getInstance() {
        return b;
    }

    private B() {
    }
}
