package com.biscuits.core.framework;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.PreDestroy;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author biscuits
 * @date 2020/07/08
 */
public final class JsonUtils {

    private final static ThreadLocal<Gson> cache = new ThreadLocal<>();

    private static Gson getGson() {
        Gson gson = cache.get();
        if (gson == null) {
            cache.remove();
            cache.set(new GsonBuilder().disableHtmlEscaping().create());
        }
        return cache.get();
    }

    public static String toJson(Object obj) {
        if (obj != null && String.class.equals(obj.getClass())) {
            throw new RuntimeException("当前对象为String,不允许使用此方法");
        }
        return getGson().toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clz) {

        return getGson().fromJson(json, clz);
    }


    public static <T> T fromJson(String json, Type type) {
        return getGson().fromJson(json, type);
    }


    public static void remove() {
        cache.remove();
    }


    /**
     * 线程销毁
     */
    @PreDestroy
    public void destory() {
        cache.remove();
    }

    private static class JsonMap extends HashMap<String, Object> {

    }

}
