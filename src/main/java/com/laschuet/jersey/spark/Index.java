package com.laschuet.jersey.spark;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class Index extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(Example.class);
        return classes;
    }
}