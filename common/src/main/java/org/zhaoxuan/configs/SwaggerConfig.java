package org.zhaoxuan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${module.name}")
    private String moduleName;

    @Bean(value = "DocumentAPI")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zhaoxuan.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title(moduleName)
                .description(moduleName + " 微服务 API 文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
