package ru.education;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

    }
    //запускаем дебаг, в браузере http://localhost:8080/api/v1/hello
    //получаем "Hello, World!"
}
