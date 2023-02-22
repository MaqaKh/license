package com.thoughtmechanix.licenses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TMXIdFilter implements Filter {
    private static final Logger logger =
            LoggerFactory.getLogger(TMXIdFilter.class);

    private static final String TMX_ID_HEADER_NAME = "TMX-ID";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String id = httpServletRequest.getHeader(TMX_ID_HEADER_NAME);
        logger.info("id: "+ id);
        // Do something with the TMX-ID header value
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
