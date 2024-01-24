package ru.parfenov.CharCounter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.parfenov.CharCounter.CharCounterApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CharCounterApplication.class)
class CounterServiceTest {
    @Autowired
    private CounterService service;

    @Test
    void whenFull() {
        assertEquals(service.getCount("qwertyqwe"),
                "\"q\": 2, \"e\": 2, \"w\": 2, \"r\": 1, \"t\": 1, \"y\": 1");
    }

    @Test
    void whenEmpty() {
        assertEquals(service.getCount(""), "An empty string was passed!");
    }
}