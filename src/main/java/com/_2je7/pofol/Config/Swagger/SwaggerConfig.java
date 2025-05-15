package com._2je7.pofol.Config.Swagger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

/**
 * Spring Boot 3.x + Springdoc OpenAPI Swagger 설정
 */
@Configuration
@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {

    /**
     * 기본 OpenAPI 정보 설정
     */
    @Bean
    public OpenAPI customOpenAPI() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new OpenAPI()
            .info(new Info()
                .title("2JE7 PORTFOLIO REST API")
                .description("2JE7 PORTFOLIO REST API - Restart Date : " + sdf.format(new Date()))
                .version("2025.02.03"))
            .addSecurityItem(new SecurityRequirement().addList("JWT"));
    }

    /**
     * API 그룹 설정
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/api/**") // 필요한 경로 패턴을 지정
            .packagesToScan("com._2je7.pofol.Controller") // 컨트롤러 패키지 지정
            .build();
    }

    /**
     * Springdoc Swagger UI 설정 (Springfox의 UiConfiguration 대체)
     */
//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//            .group("admin")
//            .pathsToMatch("/admin/**")
//            .build();
//    }
}