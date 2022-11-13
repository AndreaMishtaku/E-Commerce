package com.web.ecommerceapp.config;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "E-commerce by Andre APIs",
                "E-commerce APIs Documentation",
                "1",
                "Terms of service",
                new Contact("Andrea Mishtaku", "www.google.com", "mishtakuandrea@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .securityContexts(Arrays.asList(this.securityContext()))
                .securitySchemes(Arrays.asList(this.apiKey()))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(this.defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
