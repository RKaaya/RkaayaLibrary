package rkaaya.springframework.springbeanlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import rkaaya.springframework.springbeanlifecycle.controllers.PetController;

@SpringBootApplication
public class SpringBeanLifeCycleApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringBeanLifeCycleApplication.class, args);
        System.out.println(ctx.getBean(PetController.class).say());
    }

}
