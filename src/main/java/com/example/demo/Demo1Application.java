package com.example.demo;

import com.example.demo.Service.GuildMemberService;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

//    @Bean
//    public ApplicationListener<ApplicationReadyEvent> sendDataToSecondMicroserviceOnStartup() {
//        return event -> {
//            RestTemplate restTemplate = new RestTemplate();
//            String serverUrl = "http://localhost:8080/api/receiveData";
//            GuildMemberService guildMemberService = new GuildMemberService();
//            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, guildMemberService.processLuaFile(), String.class);
//            if (response.getStatusCode().is2xxSuccessful()) {
//                System.exit(0); // Явное завершение работы микросервиса
//            }
//        };
//    }

}
