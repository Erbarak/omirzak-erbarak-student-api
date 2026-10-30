package com.omirzak.studentapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createStudentReturnsCreated() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Erbarak Omirzak\",\"studentCode\":\"37403\",\"email\":\"e@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Erbarak Omirzak"));
    }

    @Test
    void missingStudentReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/students/999999"))
                .andExpect(status().isNotFound());
    }
}
