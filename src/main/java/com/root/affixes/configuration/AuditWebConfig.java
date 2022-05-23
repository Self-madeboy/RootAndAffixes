package com.root.affixes.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfigAdapter
 *
 * @author jc
 * @date 2018-09-21
 */
@Configuration
@EnableWebMvc
public class AuditWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("POST", "PUT", "GET", "DELETE", "OPTIONS")
                .allowedOrigins("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/sso/**")
                .allowedMethods("POST", "PUT", "GET", "DELETE", "OPTIONS")
                .allowedOrigins("*")
                .allowCredentials(false)
                .maxAge(3600);
    }

//    @Bean
//    @Primary
//    @ConditionalOnClass(ObjectMapperFactory.class)
//    @ConditionalOnProperty(value = "matech.framework.json.dynamic-filter")
//    @ConditionalOnMissingBean(name = "objectMapper")
//    ObjectMapper objectMapper() {
//        return ObjectMapperFactory.getDefaultObjectMapper();
//    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // 替换ObjectMapper
//        converters.forEach(c -> {
//            if (c instanceof MappingJackson2HttpMessageConverter) {
//                ((MappingJackson2HttpMessageConverter) c).setObjectMapper(objectMapper());
//            }
//        });
//    }
}
