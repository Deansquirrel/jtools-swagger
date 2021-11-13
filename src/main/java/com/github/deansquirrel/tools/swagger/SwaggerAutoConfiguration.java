package com.github.deansquirrel.tools.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ISwaggerConfig swaggerConfig() {
        return new ISwaggerConfig() {
            @Override
            public Boolean getEnable() {
                return null;
            }

            @Override
            public String getTitle() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public String getVersion() {
                return null;
            }

            @Override
            public String getBasePackage() {
                return null;
            }
        };
    }
}
