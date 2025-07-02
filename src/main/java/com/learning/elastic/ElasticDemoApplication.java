package com.learning.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.learning.elastic.repository")
@EnableElasticsearchRepositories(basePackages = "com.learning.elastic.search")
public class ElasticDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticDemoApplication.class, args);
    }

}
