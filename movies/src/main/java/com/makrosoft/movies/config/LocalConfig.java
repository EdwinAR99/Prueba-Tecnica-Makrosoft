package com.makrosoft.movies.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LocalConfig {

    // ~ Beans
    // ====================================================================
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("validation", "exceptions");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
