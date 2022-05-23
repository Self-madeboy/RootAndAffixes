package com.root.affixes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
@EnableScheduling
@EnableCaching
public class AffixesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AffixesApplication.class, args);
    }

}
