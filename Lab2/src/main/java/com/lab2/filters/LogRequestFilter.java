package com.lab2.filters;

import com.lab2.services.LogService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "LogRequestFilter")
public class LogRequestFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        LogService.logRequest(request);
        chain.doFilter(request, response);
    }
}
