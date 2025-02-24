package com.example.AuthQuerySender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthQuerySender.model.*;;

@RestController
public class AuthQueryController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // In a real application, you would check against a database
        if ("user".equals(loginRequest.getUsername()) &&
                passwordEncoder.matches(loginRequest.getPassword(), passwordEncoder.encode("password"))) {
            return "Authentication successful";
        }
        return "Authentication failed";
    }

    @MessageMapping("/sendQuery")
    @SendTo("/topic/query")
    public QueryRequest sendQuery(QueryRequest queryRequest) {
        // Here you could add logging or additional processing
        return queryRequest;
    }
}
