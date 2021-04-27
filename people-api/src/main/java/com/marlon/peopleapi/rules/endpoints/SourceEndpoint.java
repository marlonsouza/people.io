package com.marlon.peopleapi.rules.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceEndpoint {

    @GetMapping("/source")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("https://github.com/marlonsouza/people.io");
    }
}

