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
        var out = response.getWriter();
        out.write("<p>Lorem ipsum dolor sit amet</p>");
        SimpleResponseWrapper wrapper = new SimpleResponseWrapper((HttpServletResponse) response);
        String content = wrapper.toString();

        chain.doFilter(request, response);

        content += "<p>Lorem ipsum dolor sit amet</p>";
        PrintWriter pw = response.getWriter();
        pw.write(content);
    }
}
