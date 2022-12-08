package com.javatech.lab8.filters;

import com.javatech.lab8.annotations.JWTAuthRequired;
import com.javatech.lab8.exceptions.AccountInvalidTokenException;
import com.javatech.lab8.exceptions.AuthorizationMissingTokenException;
import com.javatech.lab8.tokens.TokenHandler;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.security.Principal;

@Provider
@JWTAuthRequired
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {


    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws AuthorizationMissingTokenException {

        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isTokenBasedAuthentication(authorizationHeader)) {
            throw new AuthorizationMissingTokenException();
        }

        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        validateToken(token, requestContext);
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }


    private void validateToken(String token, ContainerRequestContext context) throws AccountInvalidTokenException {
        Long id = TokenHandler.validate(token);

        context.setProperty("account_id", id);

        context.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> String.valueOf(id);
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }

        });

    }
}