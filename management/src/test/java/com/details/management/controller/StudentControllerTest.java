package com.details.management.controller;

import com.details.management.dto.StudentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

    MockMvc mvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected StudentController studentController;

    StudentDto studentDto;
    Integer studentId;

    @Before
    public void setUp() throws Exception {
        this.mvc = standaloneSetup(this.studentController).build();
        studentDto = new StudentDto();
        studentDto.setFirstName("Karthik");

    }

    @Order(3)
    public void find() throws Exception{
        findAll();
        mvc.perform( MockMvcRequestBuilders
                .get("/studentDetails/"+studentId)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(studentId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(studentDto.getFirstName()));
    }

    @Test
    @Order(2)
    public void findAll() throws Exception {
        add();
        MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders
                .get("/studentDetails/getAllRecords")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].studentId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].studentId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value(studentDto.getFirstName())).andReturn();

        List<Object> objectList = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsByteArray(), List.class);
        studentId = (Integer) ((Map)objectList.get(0)).get("studentId");

    }

    @Test
    @Order(1)
    public void add() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/studentDetails/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(studentDto))
                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(studentDto.getFirstName()));
    }
}
