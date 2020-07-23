package com.ml.corda.client.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Our Spring Boot application.
 */
@SpringBootApplication(scanBasePackages = "com.ml")
@EnableSwagger2
public class Server {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(args);
    }
}
