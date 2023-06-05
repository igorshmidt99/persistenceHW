package org.example;

import org.example.position.model.Position;
import org.example.project.model.Project;
import org.example.user.model.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example")
public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Position.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Project.class);
        return configuration.buildSessionFactory();
    }
}