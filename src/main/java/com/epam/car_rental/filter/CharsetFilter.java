package com.epam.car_rental.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("pageEncoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
