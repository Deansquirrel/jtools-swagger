package com.github.deansquirrel.tools.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerBean {

    private static final String defaultTitle= "API";
    private static final String defaultDescription = "";
    private static final String defaultVersion = "";

    private final ISwaggerConfig swaggerConfig;

    public SwaggerBean(ISwaggerConfig swaggerConfig) {
        this.swaggerConfig = swaggerConfig;
    }

    @Bean
    public Docket createAdminRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.enable(this.swaggerConfig != null && this.swaggerConfig.getEnable() != null && this.swaggerConfig.getEnable())
                .apiInfo(this.apiInfo());

        int length = (swaggerConfig == null || swaggerConfig.getControllerTags() == null) ? 0 : swaggerConfig.getControllerTags().length;
        if (length > 0) {
            Tag tag = swaggerConfig.getControllerTags()[0];
            if (length > 1) {
                Tag[] tags = new Tag[length - 1];
                for (int i = 1; i < length; i++) {
                    tags[i - 1] = swaggerConfig.getControllerTags()[i];
                }
                docket.tags(tag, tags);
            } else {
                docket.tags(tag);
            }
        }

        String basePackage = (this.swaggerConfig == null || this.swaggerConfig.getBasePackage() == null)?
                "com":this.swaggerConfig.getBasePackage();

        return docket
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.swaggerConfig == null ? SwaggerBean.defaultTitle
                        : this.swaggerConfig.getTitle() == null ? SwaggerBean.defaultTitle
                        : this.swaggerConfig.getTitle())
                .description(this.swaggerConfig == null ? SwaggerBean.defaultDescription
                        : this.swaggerConfig.getDescription() == null ? SwaggerBean.defaultDescription
                        : this.swaggerConfig.getDescription())
                .version(this.swaggerConfig == null ? SwaggerBean.defaultVersion
                        : this.swaggerConfig.getVersion() == null ? SwaggerBean.defaultVersion
                        : this.swaggerConfig.getVersion())
                .build();
    }

}
