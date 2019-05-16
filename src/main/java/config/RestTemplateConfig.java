package com.sensetime.fis.senseguard.opendoor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 11:22
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {
    @Autowired
    private HttpMessageConverters fastJsonHttpMessageConverter;
    @Bean(value = "restTemplate")
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(fastJsonHttpMessageConverter.getConverters());
        return restTemplate;
    }
}
