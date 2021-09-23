package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Demo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class cfg{
    public String xx;
    public cfg(@Value("${xx}") String xx) {
        this.xx=xx;
    }
}

@Component
class MyRunner implements CommandLineRunner {
    private ApplicationContext context;
    MyRunner(ApplicationContext context){
        this.context=context;
    }

    @Override
    public void run(String... args) throws Exception {
        cfg c= context.getBean(cfg.class);
        System.out.println(c.xx);
        c= context.getBean(cfg.class);  //Error
    }
}
