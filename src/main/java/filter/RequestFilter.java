package com.sensetime.fis.senseguard.opendoor.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 11:33
 * @Version 1.0
 */
@Slf4j
@Configuration
@WebFilter(urlPatterns = "/**",filterName = "RequestLogFilter")
public class RequestFilter implements Filter {
    /**
     * 打印出请求参数
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest)request);
        String accessToken = requestWrapper.getHeader("accessToken");
        String uri = requestWrapper.getRequestURI();
        Enumeration<String> parameterNames = requestWrapper.getParameterNames();
        HashMap queryParameter = new HashMap(16);

        while(parameterNames.hasMoreElements()) {
            String key = (String)parameterNames.nextElement();
            queryParameter.put(key, requestWrapper.getParameter(key));
        }

        log.info("--> request uri:[{}],accessToken[{}],query parameter:[{}],body paramater:[{}]", new Object[]{uri, accessToken, queryParameter, requestWrapper.getBody()});
        chain.doFilter(requestWrapper, response);
    }
}
