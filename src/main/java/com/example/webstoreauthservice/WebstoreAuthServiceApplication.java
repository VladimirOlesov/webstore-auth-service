package com.example.webstoreauthservice;

import com.example.commoncode.config.CommonCodeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {WebstoreAuthServiceApplication.class,
    CommonCodeConfig.class})
public class WebstoreAuthServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebstoreAuthServiceApplication.class, args);
  }

}
