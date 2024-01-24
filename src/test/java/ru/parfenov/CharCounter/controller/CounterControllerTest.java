package ru.parfenov.CharCounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.parfenov.CharCounter.CharCounterApplication;
import ru.parfenov.CharCounter.service.CounterService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CharCounterApplication.class)
@AutoConfigureMockMvc
class CounterControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean(answer = Answers.CALLS_REAL_METHODS)
    private CounterService service;

    @Test
    void whenInputLineAndTakeOk() throws Exception {

        when(service.getCount("qwertyqwe")).thenReturn(
                "\"q\": 2, \"e\": 2, \"w\": 2, \"r\": 1, \"t\": 1, \"y\": 1"
        );
        MockHttpServletRequestBuilder mockRequest = post("/input")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.TEXT_PLAIN)
                .content(this.mapper.writeValueAsString("qwertyqwe"));
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(content().string("\"q\": 2, \"e\": 2, \"w\": 2, \"r\": 1, \"t\": 1, \"y\": 1"));
    }

    @Test
    void whenInputEmptyAndTakeOk() throws Exception {

        when(service.getCount("")).thenReturn(" ");
        MockHttpServletRequestBuilder mockRequest = post("/input")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.TEXT_PLAIN)
                .content(this.mapper.writeValueAsString(""));
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(content().string("An empty string was passed!"));
    }
}