package com.xy.marry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "com.xy.marry.mapper")
@ServletComponentScan("com.xy.marry.implement.filters")
public class MarryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarryApplication.class, args);
	}

}
