package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages= {"com.my.mapper"})
public class ProDeskWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProDeskWebApplication.class, args);
	}
}
