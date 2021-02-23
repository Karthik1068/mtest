package com.details.management.controller;

import com.details.management.ManagementApplication;
import com.details.management.config.JwtTokenUtil;
import com.details.management.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ManagementApplication.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    protected StudentController studentController;

    @Autowired
    protected JwtTokenUtil jwtTokenUtil;

    Student student;
    Integer studentId;

    //DATA
    String student1 = "{\n" +
            "  \"studentID\": 1,\n" +
            "  \"firstName\": \"Kumaresan\",\n" +
            "  \"lastName\": \"Murugan\",\n" +
            "  \"phone\": 9842647726\n" +
            "}";
    String student2 = "{\n" +
            "  \"studentID\": 2,\n" +
            "  \"firstName\": \"Thirumurthy\",\n" +
            "  \"lastName\": \"Selvaraj\",\n" +
            "  \"phone\": 9095065068\n" +
            "}";

    String instructor1 = "{\n" +
            "  \"instructorID\": 3,\n" +
            "  \"headedBy\": \"Karthikeyan\",\n" +
            "  \"firstName\": \"Prabu\",\n" +
            "  \"lastName\": \"Manickam\",\n" +
            "  \"phone\": \"7502038800\",\n" +
            "  \"department\": {\n" +
            "    \"name\": \"Computer Science\",\n" +
            "    \"location\": \"Chennai\"\n" +
            "  }\n" +
            "}";

    String instructor2 = "{\n" +
            "      \"instructorID\": 3,\n" +
            "      \"headedBy\": \"Pradeep\",\n" +
            "      \"firstName\": \"Karthikeyan\",\n" +
            "      \"lastName\": \"Manickam\",\n" +
            "      \"phone\": \"7502038800\",\n" +
            "      \"department\": {\n" +
            "        \"name\": \"Civil Engineering\",\n" +
            "        \"location\": \"Chennai\"\n" +
            "      }\n" +
            "    }";

    String course1 = "{\n" +
            "    \"courseID\": 2,\n" +
            "    \"duration\": 10,\n" +
            "    \"name\": \"Scheduling\",\n" +
            "    \"instructor\": "+instructor1+",\n" +
            "    \"department\": {\n" +
            "      \"name\": \"Computer Science\",\n" +
            "      \"location\": \"Chennai\"\n" +
            "    }\n" +
            "  }";

    String course2 = "{\n" +
            "    \"courseID\": 4,\n" +
            "    \"duration\": 20,\n" +
            "    \"name\": \"AutoCAD designing\",\n" +
            "    \"instructor\": "+instructor2+",\n" +
            "    \"department\": {\n" +
            "      \"name\": \"Civil Engineering\",\n" +
            "      \"location\": \"Chennai\"\n" +
            "    }\n" +
            "  }";

    String course3 = "{\n" +
            "    \"courseID\": 2,\n" +
            "    \"duration\": 40,\n" +
            "    \"name\": \"Operating Systems\",\n" +
            "    \"instructor\": "+instructor1+",\n" +
            "    \"department\": {\n" +
            "      \"name\": \"Computer Science\",\n" +
            "      \"location\": \"Chennai\"\n" +
            "    }\n" +
            "  }";

    String course4 = "{\n" +
            "    \"courseID\": 4,\n" +
            "    \"duration\": 50,\n" +
            "    \"name\": \"Surveying\",\n" +
            "    \"instructor\": "+instructor2+",\n" +
            "    \"department\": {\n" +
            "      \"name\": \"Civil Engineering\",\n" +
            "      \"location\": \"Chennai\"\n" +
            "    }\n" +
            "  }";

    String jwtToken;

    @Before
    public void setUp() throws Exception {
        //this.mvc = standaloneSetup(this.studentController).build();
        jwtToken  = mvc.perform( MockMvcRequestBuilders
                        .post("/authenticate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"password\": \"welcome\",\n" +
                                "  \"username\": \"testuser\"\n" +
                                "}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()
                .replace("{\"token\":\"", "")
                .replace("\"}", "");
        setupInstructors();
    }

    public void setupInstructors() throws Exception{

        mvc.perform( MockMvcRequestBuilders
                .post("/api/v1/academy/instructors")
                .header("Authorization", "Bearer "+jwtToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(instructor1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Prabu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Manickam"));
    }

    @Test
    public void justTest() {

    }


}
