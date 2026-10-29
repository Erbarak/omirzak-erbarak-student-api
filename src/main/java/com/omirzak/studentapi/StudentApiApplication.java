package com.omirzak.studentapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    // Seed one record with the student's own data when the table is empty
    @Bean
    CommandLineRunner seed(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Student("Erbarak Omirzak", "37403", "erbarak.omirzak@example.com"));
            }
        };
    }
}
