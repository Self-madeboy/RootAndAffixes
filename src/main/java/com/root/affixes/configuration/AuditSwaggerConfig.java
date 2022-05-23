package com.root.affixes.configuration;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SwaggerConfig
 *
 * @author zhou zhu
 * @date 2020-11-16
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

    private final TypeResolver typeResolver;

    @Autowired
    public AuditSwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }


    @Bean
    public Docket graphApi() {
        return this.commonApi("graph Management", PROJECT_API_PACKAGE + ".graph", false);
    }

    private Docket commonApi(String groupName, String basePackage, boolean requireToken) {
        return this.buildApiCommonPart(new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)), requireToken);
    }

    private Docket buildApiCommonPart(ApiSelectorBuilder builder, boolean requireToken) {
        Docket docket = builder.paths(PathSelectors.any())
                .build()
                .consumes(
                        Stream.of(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .collect(Collectors.toSet()))
                .produces(
                        Stream.of(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .collect(Collectors.toSet()));

        if (requireToken) {
            docket.globalOperationParameters(
                    Stream.of(
                            new ParameterBuilder()
                                    .name(TOKEN_HEADER)
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(true)
                                    .description("JWT令牌")
                                    .defaultValue(TOKEN_TYPE + " ")
                                    .build()
                    ).collect(Collectors.toList()));
        }
//        docket.globalOperationParameters(
//                Stream.of(
//                        new ParameterBuilder()
//                                .name(WORKING_ORGANIZATION)
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header")
//                                .required(false)
//                                .description("当前工作组织")
//                                .build()
//                ).collect(Collectors.toList()));

//        docket.globalOperationParameters(
//                Stream.of(
//                        new ParameterBuilder()
//                                .name(USER_TYPE)
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header")
//                                .required(false)
//                                .description("当前用户类型")
//                                .build()
//                ).collect(Collectors.toList()));
//
//        docket.globalOperationParameters(
//                Stream.of(
//                        new ParameterBuilder()
//                                .name(BUDGET_UNIT_ID)
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header")
//                                .required(false)
//                                .description("当前被审单位id（当用户类型为被审单位用户才有值）")
//                                .build()
//                ).collect(Collectors.toList()));


        // 潜在的功能enableUrlTemplating（将来可能不被支持），开启不同的查询参数生成不同的API
        return docket.pathMapping("/")
//                .additionalModels(typeResolver.resolve(CommonErrorDTO.class))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .license(API_LICENSE)
//                .licenseUrl(API_LICENSE_URL)
                .version(API_VERSION)
                .build();
    }

    private List<ResponseMessage> responseMessages() {
        return Stream.of(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .responseModel(new ModelRef(COMMON_ERROR_CLASS))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .responseModel(new ModelRef(COMMON_ERROR_CLASS))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .responseModel(new ModelRef(COMMON_ERROR_CLASS))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .message(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .responseModel(new ModelRef(COMMON_ERROR_CLASS))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .responseModel(new ModelRef(COMMON_ERROR_CLASS))
                        .build()).collect(Collectors.toList());
    }

    @Bean
    public Docket createRestApi() {

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").
                modelRef(new ModelRef("string")).
                parameterType("header").required(false).build();

        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // .apis(RequestHandlerSelectors.basePackage("com.open.capacity"))
                .apis(RequestHandlerSelectors.any())
                .paths(input -> PathSelectors.regex("/user.*").apply(input) || PathSelectors.regex("/permissions.*").apply(input)
                        || PathSelectors.regex("/roles.*").apply(input) || PathSelectors.regex("/test.*").apply(input)
                )
                // .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
