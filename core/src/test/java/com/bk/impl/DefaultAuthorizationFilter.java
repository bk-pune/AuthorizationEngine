package com.bk.impl;

import com.bk.cache.PrincipalCache;
import com.bk.filter.AuthorizationFilter;
import com.bk.identity.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/04/22
 */
public class DefaultAuthorizationFilter extends AuthorizationFilter {

    private PrincipalCache principalCache;

    public DefaultAuthorizationFilter(String basePackage) {
        super(basePackage);
        principalCache = new PrincipalCache();
    }

    @Override
    public boolean isBypassUrl(String url) {
        return false;
    }

    @Override
    public Principal extractPrincipal(HttpServletRequest request, HttpServletResponse response) {
        return principalCache.getPrincipal(request.getParameter("username"));
    }
}
