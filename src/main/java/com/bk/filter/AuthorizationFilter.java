package com.bk.filter;

import com.bk.engine.AuthorizationEngine;
import com.bk.engine.AuthorizationEngineImpl;
import com.bk.policy.AuthorizationPolicy;
import com.bk.registry.AuthorizationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter responsible for verifying Authorization of the current request.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 08/04/22
 */
public abstract class AuthorizationFilter implements Filter {

    private AuthorizationEngine authorizationEngine;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initAuthorizationModel();
    }

    private void initAuthorizationModel() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        authorizationEngine = new AuthorizationEngineImpl(new AuthorizationModel(requestMappingHandlerMapping));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(isBypassUrl(httpServletRequest.getRequestURI())) {
           chain.doFilter(request, response);
        }
        else {
            AuthorizationPolicy authorizationPolicy = extractPolicy(httpServletRequest, httpServletResponse);
            boolean authorized = authorizationEngine.isAuthorized(authorizationPolicy, httpServletRequest.getRequestURI());
            if (authorized) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // generate unauthorized response
            }
        }
    }

    public abstract boolean isBypassUrl(String url);

    /**
     * Extract the authorization policy for currently logged in Principal.<br>
     * Usually, the auth token sent along with the request will have the principal identification information.
     * The authorization policy should be part of the Principal.
     * @param request
     * @param response
     * @return AuthorizationPolicy obtained from the current request. If no such policy found, returns null.
     */
    public abstract AuthorizationPolicy extractPolicy(HttpServletRequest request, HttpServletResponse response);
}
