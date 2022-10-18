package com.lab2.filters;

import com.lab2.utilities.SimpleResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "DecorateResponseFilter")
public class DecorateResponseFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        SimpleResponseWrapper wrapper = new SimpleResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, wrapper);

        var out = response.getWriter();
        String content = wrapper.toString();
        out.write("<p>Lorem ipsum dolor sit amet</p>" + content + "<p>Lorem ipsum dolor sit amet</p>");
    }
}
