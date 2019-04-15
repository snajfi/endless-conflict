package cz.endless.conflict.filters;

import cz.endless.conflict.beans.LoggedPlayerBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Http filter which should handle basic authentication.
 */
@WebFilter("/game/*")
@Named
@RequestScoped
public class AuthFilter implements Filter {

    @Inject private LoggedPlayerBean loggedPlayerBean;

    @Override
    public void init(FilterConfig filterConfig) {
        // no action
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (loggedPlayerBean.getLoggedPlayer()==null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/index.jsf");
            return;
        }
        if (loggedPlayerBean.getLoggedPlayer().isAdministrator()) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/administration/index.jsf");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        // no action
    }
}
