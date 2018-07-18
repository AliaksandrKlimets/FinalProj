package com.epam.car_rental.filter;

import com.epam.car_rental.controller.command.AccessNotAllowedException;
import com.epam.car_rental.controller.command.CommandDirector;
import com.epam.car_rental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.COMMAND;
import static com.epam.car_rental.controller.constant.ControlConst.USER;

public class AccessFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command = servletRequest.getParameter(COMMAND);
        if(command == null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        User user = (User) session.getAttribute(USER);
        CommandDirector director = CommandDirector.getInstance();
        try {
            director.checkAccess(command, user);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AccessNotAllowedException e) {
            LOGGER.error(e.getMessage(), e);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}
