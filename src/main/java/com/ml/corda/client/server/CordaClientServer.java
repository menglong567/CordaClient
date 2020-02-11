package com.ml.corda.client.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengl
 */
@SpringBootApplication(scanBasePackages = "com.ml")
public class CordaClientServer {
    public static void main(String[] args) {
        SpringApplication.run(CordaClientServer.class, args);
    }
}

