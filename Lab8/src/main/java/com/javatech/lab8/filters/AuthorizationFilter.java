package com.javatech.lab8.filters;

import com.javatech.lab8.annotations.JWTAuthRequired;
import com.javatech.lab8.exceptions.AccountNotAllowedException;
import com.javatech.lab8.pemissions.Role;
import com.javatech.lab8.service.AccountService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Provider
@JWTAuthRequired
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    AccountService accountService;
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws AccountNotAllowedException {

        Method method = resourceInfo.getResourceMethod();
        if (method != null) {

            Principal principal = requestContext.getSecurityContext().getUserPrincipal();

            JWTAuthRequired JWTContext = method.getAnnotation(JWTAuthRequired.class);
            Role[] permissions = JWTContext.Permissions();


            checkPermissions(permissions, Long.valueOf(principal.getName()));

        }
    }

    private void checkPermissions(Role[] permissions, Long id) throws AccountNotAllowedException {
        List<Role> permissionsList = Arrays.asList(permissions);

        Role userPermission = accountService.getAccountRole(id);


        if (!permissionsList.contains(userPermission)) {
            throw new AccountNotAllowedException();
        }
    }


}