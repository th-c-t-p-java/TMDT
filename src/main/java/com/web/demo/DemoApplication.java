package com.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ImportResource( { "classpath*:spring-config.xml"} )
@PropertySource("classpath:jdbc.properties")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
