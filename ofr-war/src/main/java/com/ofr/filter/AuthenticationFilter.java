package com.ofr.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/16/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("in Authentication Filter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        String requestedUri = request.getRequestURI();
        System.out.println("request uri : " + requestedUri);


        if (requestedUri.contains("resource")) {
            System.out.println("in if");
            filterChain.doFilter(servletRequest, servletResponse);
        }

        if(session != null){
            if(session.getAttribute("authenticatedUserRole") != null){

                System.out.println("sssssssssssssssssssssssssssssssssssssss");

                if((requestedUri.contains("admin") || requestedUri.contains("index")) && session.getAttribute("authenticatedUserRole").equals("user")){
                    System.out.println("***********************************");
                    response.sendRedirect("/ofr/home");
                    return;
                }
                else if((!requestedUri.contains("admin") || requestedUri.contains("index")) && session.getAttribute("authenticatedUserRole").equals("admin")){
                    System.out.println("#######################################");
                    response.sendRedirect("/ofr/admin/home");
                    return;
                }
            }
        }

        System.out.println("before last line");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
