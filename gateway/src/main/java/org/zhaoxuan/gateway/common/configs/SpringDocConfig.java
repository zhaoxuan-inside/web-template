package org.zhaoxuan.gateway.common.configs;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.*;
import org.springframework.cloud.gateway.route.*;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class SpringDocConfig {

    @Bean
    public SwaggerUiConfigParameters setSwaggerUiConfigParameters() {
        return new SwaggerUiConfigParameters(new SwaggerUiConfigProperties());
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new CopyOnWriteArrayList<>();

        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        if (ObjectUtil.isEmpty(definitions)) {
            return null;
        }

        definitions.forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-route", "");
            swaggerUiConfigParameters.addGroup(name);
            groups.add(GroupedOpenApi.builder()
                    .pathsToMatch("/" + name + "/**")
                    .group(name + "/v3/api-docs")
                    .build());
        });
        return groups;
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("gateway")
                .description("springdoc gateway API")
                .version("v1.0.0"));
    }
}