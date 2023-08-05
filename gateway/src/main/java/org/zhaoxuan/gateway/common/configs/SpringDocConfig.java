package org.zhaoxuan.gateway.common.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SpringDocConfig {
//    @Bean
//    @Lazy(false)
//    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//
//        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
//                .forEach(routeDefinition -> {
//                    String name = routeDefinition.getId().replaceAll("-service", "");
//                    swaggerUiConfigParameters.addGroup(name);
//                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
//                });
//        return groups;
//    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("springdoc gateway API")
                        .description("springdoc gateway API")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("姓名")
                                .email("邮箱")));
    }
}
