package com.biscuits.starter;

import com.biscuits.core.framework.container.AbstractContainer;

import java.util.Set;

public class Starter extends AbstractContainer {
    private static Starter starter = new Starter();


    public static Starter getInstance() {
        return starter;
    }

    private Starter() {
    }

    @Override
    public Set<String> init() {
        throw new RuntimeException("Method Not Support");
    }
}
