package org.avol.tweet.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Durga on 9/14/2015.
 */
public class RequestTraxnIdFilter implements Filter {

    private static Random random = new Random(1000);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Nothing for now.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //you can write your filter stuff here.
        request.setAttribute("TranxId", random.nextInt()); //TODO: make use of MDC
        try {
            chain.doFilter(request, response);
        } finally {
            request.removeAttribute("TranxId");
        }
    }

    @Override
    public void destroy() {
        //nothing for now.
    }
}
