package com.lambdaschool.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import io.restassured.module.mockmvc.RestAssuredMockMvc;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest {

    @Autowired private WebApplicationContext webApplicationContext;

    @Before
    public void init(){
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void measuredResponseTimeFindAll(){
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }

    @Test
    public void measuredResponseTimeSave() throws JsonProcessingException {
        Course temp=new Course();

        temp.setInstructor(new Instructor("Demo"));
        temp.setCoursename("Tea Pot");
        temp.setCourseid(418);
        ObjectMapper mapper=new ObjectMapper();
        String sender=mapper.writeValueAsString(temp);
        given().when().post("/courses/course/add",temp).then().time(lessThan(5000L));
    }
}