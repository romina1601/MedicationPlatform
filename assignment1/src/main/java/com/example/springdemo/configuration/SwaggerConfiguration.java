//package com.example.springdemo.configuration;
//
//import com.google.common.collect.Sets;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.ModelRendering;
//import springfox.documentation.swagger.web.UiConfiguration;
//import springfox.documentation.swagger.web.UiConfigurationBuilder;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
///**
// * Swagger configuration  class
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//
//    /**
//     * @return interface bean to integrate swagger
//     */
//    @Bean
//    public Docket bankingServiceDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
//                .useDefaultResponseMessages(false)
//                .tags( SwaggerTags.PERSON, SwaggerTags.USERS,
//                        SwaggerTags.DOCTOR,
//                        SwaggerTags.CAREGIVER,
//                        SwaggerTags.PATIENT,
//                        SwaggerTags.MEDICATION,
//                        SwaggerTags.MEDICATION_PLAN,
//                        SwaggerTags.LOGIN)
//                .forCodeGeneration(true)
//                .apiInfo(apiInfo())
//                .select().apis(RequestHandlerSelectors.basePackage("com.example.springdemo")).build();
//
//    }
//
//    /**
//     * @return Medical platform API info (title)
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("UTCN lab DS Medical platform").build();
//    }
//
//    /**
//     * Swagger UI configuration that customizes some ui behaviour
//     *
//     * @return {@link UiConfiguration} object
//     */
//    @Bean
//    UiConfiguration uiConfig() {
//        return UiConfigurationBuilder.builder()
//                .deepLinking(true)
//                .displayOperationId(false)
//                .defaultModelsExpandDepth(1)
//                .defaultModelExpandDepth(1)
//                .defaultModelRendering(ModelRendering.MODEL)
//                .displayRequestDuration(false)
//                .displayOperationId(false)
//                .build();
//    }
//}
