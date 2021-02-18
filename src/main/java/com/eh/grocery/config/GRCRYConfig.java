package com.eh.grocery.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Configuration
@ComponentScan(basePackages= {"com.eh.grocery.dev.config"})
public class GRCRYConfig {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(GRCRYConfig.class);
}
