package com.javatech.lab8;

import com.javatech.lab8.exceptions.mappers.CustomBadRequestMapper;
import com.javatech.lab8.exceptions.mappers.CustomConflictMapper;
import com.javatech.lab8.exceptions.mappers.CustomNotFoundMapper;
import com.javatech.lab8.exceptions.mappers.CustomUnauthorizedMapper;

import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("api/v1")
@ApplicationScoped
public class ApplicationConfig extends Application {

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.javatech.lab8.controller.DocumentController.class);
        resources.add(com.javatech.lab8.controller.UserController.class);
        resources.add(com.javatech.lab8.exceptions.mappers.CustomBadRequestMapper.class);
        resources.add(com.javatech.lab8.exceptions.mappers.CustomConflictMapper.class);
        resources.add(com.javatech.lab8.exceptions.mappers.CustomNotFoundMapper.class);
        resources.add(com.javatech.lab8.exceptions.mappers.CustomUnauthorizedMapper.class);
        resources.add(com.javatech.lab8.filters.AuthenticationFilter.class);
        resources.add(com.javatech.lab8.filters.AuthorizationFilter.class);
    }
}
