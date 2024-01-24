package ru.parfenov.CharCounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.parfenov.CharCounter.service.CounterService;

import java.io.IOException;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class CounterController {
    private final CounterService service;
    private final ObjectMapper objectMapper;

    @PostMapping("/input")
    public ResponseEntity<String> input(@RequestBody String str) {
        return new ResponseEntity<>(this.service.getCount(str), HttpStatus.CREATED);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public void exceptionHandler(Exception e, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", "Request body is missing!!!");
                put("type", e.getClass());
            }
        }));
    }
}
