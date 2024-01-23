package ru.parfenov.CharCounter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.parfenov.CharCounter.service.CounterService;

@RestController
@AllArgsConstructor
public class CounterController {
    private final CounterService service;

    @PostMapping("/input")
    public ResponseEntity<String> input(@RequestBody String str) {
        return new ResponseEntity<>(this.service.getCount(str), HttpStatus.CREATED);
    }
}
