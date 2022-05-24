package com.root.affixes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author jc
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class AuditSwaggerConfig implements WebMvcConfigurer {

    private static final String COMMON_ERROR_CLASS = "CommonErrorDTO";

    private static final String API_TITLE = "RootAndAffixes后端";
    private static final String API_DESCRIPTION = "系统后台REST接口定义，开发桌面及移动端应用均需参考该接口规范。";
    private static final String API_LICENSE = "版权所有 @JC";
    private static final String API_VERSION = "1.0.0-SNAPSHOT";

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    private static final String PROJECT_API_PACKAGE = "com.root.affixes.controller";


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .license(API_LICENSE)
//                .licenseUrl(API_LICENSE_URL)
                .version(API_VERSION)
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里写的是API接口所在的包位置
                .apis(RequestHandlerSelectors.basePackage("com.root.affixes.controller"))
                .paths(PathSelectors.any())
                .build();
    }


}
