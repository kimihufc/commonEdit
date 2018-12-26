package com.hjl.springboot.config;

import com.alibaba.druid.filter.stat.MergeStatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: HJL
 * @create: 2018-12-25 20:25
 *
 * http://localhost:8081/druid/api.html
 */
@Configuration
public class DruidConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DruidConfiguration.class);
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUser;
    @Value("${spring.datasource.password}")
    private String jdbcPass;
    @Value("${spring.datasource.initialSize}")
    private String jdbcInitSizs;
    @Value("${spring.datasource.minIdle}")
    private String jdbcMinidle;
    @Value("${spring.datasource.maxActive}")
    private String jdbcActive;
    @Value("${spring.datasource.maxWait}")
    private String jdbcMaxwait;
    @Value("${spring.datasource.validationQuery}")
    private String jdbcQuery;

    @Bean
    public ServletRegistrationBean druidServlet() {
        log.info("init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("loginUsername", "admin");// 用户名
        initParameters.put("loginPassword", "admin");// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public MergeStatFilter creatFilter(){
        MergeStatFilter fiter = new MergeStatFilter();
        fiter.setSlowSqlMillis(10L);
        fiter.setLogSlowSql(true);
        return fiter;
    }

    @Bean
    public DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        // mysql 驱动
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // url
        dataSource.setUrl(jdbcUrl);
        // 账号
        dataSource.setUsername(jdbcUser);
        // 密码
        dataSource.setPassword(jdbcPass);
        // 初始化连接
        dataSource.setInitialSize(Integer.parseInt(jdbcInitSizs));
        // 最大线程数
        dataSource.setMaxActive(Integer.parseInt(jdbcActive));
        // 空闲时, 最大保留连接数
        dataSource.setMinIdle(Integer.parseInt(jdbcMinidle));
        // 获取连接等待时长
        dataSource.setMaxWait(Integer.parseInt(jdbcMaxwait));
        // 试探sql
        dataSource.setValidationQuery(jdbcQuery);
        // 默认开启, 设置一发
        dataSource.setTestWhileIdle(true);
        dataSource.setProxyFilters(Arrays.asList(creatFilter()));
        return dataSource;
    }
}