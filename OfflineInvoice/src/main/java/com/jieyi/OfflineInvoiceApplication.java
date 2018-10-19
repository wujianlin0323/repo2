package com.jieyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OfflineInvoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfflineInvoiceApplication.class, args);
    }
}
