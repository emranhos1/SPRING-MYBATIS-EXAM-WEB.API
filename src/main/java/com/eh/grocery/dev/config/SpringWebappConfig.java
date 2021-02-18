package com.eh.grocery.dev.config;

import java.io.File;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Configuration
@ComponentScan(basePackages = { "com.eh.grocery.dev.controller" })
public class SpringWebappConfig implements WebMvcConfigurer  {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWebappConfig.class);
    private String staticContentPath;
    private String strictContentPath;

    @Value("${user.home}/.eh/grocery/var/grcry/static/content")
    private File staticContentDir;

    @Value("${user.home}/.eh/grocery/var/grcry/static/content")
    private File strictContentDir;

    @PostConstruct
    public void init() {
        if(!staticContentDir.exists()) {staticContentDir.mkdirs();}
        if(!strictContentDir.exists()) {strictContentDir.mkdirs();}
        staticContentPath = String.format("file:%s%s", staticContentDir.getAbsoluteFile(), File.separator);
        strictContentPath = String.format("file:%s%s", strictContentDir.getAbsoluteFile(), File.separator);
        LOG.debug("Static Files Dir: {}", staticContentPath);
        LOG.debug("Static Files Dir: {}", strictContentPath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/content/**").addResourceLocations(staticContentPath);
        registry.addResourceHandler("/strict/content/**").addResourceLocations(strictContentPath);
    }
}
