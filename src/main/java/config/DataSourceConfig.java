package com.sensetime.fis.senseguard.opendoor.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sensetime.fis.senseguard.opendoor.utils.MyRSAUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;

import java.security.PrivateKey;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/8 11:53
 * @Version 1.0
 */
@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.private-key}")
    private String privateKey;

    @Value("${spring.datasource.type}")
    private String type;

    @Value("${spring.datasource.dbcp2.min-idle}")
    private Integer minIdle;
    @Value("${spring.datasource.dbcp2.initial-size}")
    private Integer initialSize;
    @Value("${spring.datasource.dbcp2.max-total}")
    private Integer maxTotal;
    @Value("${spring.datasource.dbcp2.max-wait-millis}")
    private Integer maxWaitMillis;

    @Bean("dataSource")
    public DruidDataSource druidDataSource(StandardEnvironment env) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setDbType(type);
        try {
            //加密后的内容Base64解码
            byte[] base642Byte = MyRSAUtils.base642Byte(password);
            //将Base64编码后的私钥转换成PrivateKey对象
            PrivateKey pk = MyRSAUtils.string2PrivateKey(privateKey);
            //用私钥解密
            byte[] privateDecrypt = MyRSAUtils.privateDecrypt(base642Byte, pk);
            druidDataSource.setPassword(new String(privateDecrypt,"utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxTotal);
        druidDataSource.setMaxWait(maxWaitMillis);
        druidDataSource.setMinIdle(minIdle);
        return druidDataSource;
    }
}
