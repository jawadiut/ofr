package com.ofr.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/20/13
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */

@WebFilter(filterName = "LoginFilter", servletNames = "Faces Servlet", urlPatterns = {"*.xhtml"})
public class LoginFilter implements Filter {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("in Login Filter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        String requestedUri = request.getRequestURI();

        if (session == null || session.getAttribute("authenticatedUserId") == null) {

            if (!requestedUri.contains("index")) {

                response.sendRedirect("index");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {

    }
}