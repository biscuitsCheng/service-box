package com.biscuits.core.framework.container;


import com.biscuits.core.framework.StringUtils;

public enum RunModel {
    LOCAL("local"),
    CLOUD("cloud");

    String value;

    RunModel(String value) {
        this.value = value;
    }

    public static RunModel getRunModel(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }
        RunModel[] values = RunModel.values();
        for (RunModel value : values) {
            if (value.value.equals(val)) {
                return value;
            }
        }
        return null;
    }
}
