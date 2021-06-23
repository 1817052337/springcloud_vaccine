package com.rj.bd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.rj.bd.subscribe.dao")
@MapperScan(value = "com.rj.bd.hospital.dao")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}  
