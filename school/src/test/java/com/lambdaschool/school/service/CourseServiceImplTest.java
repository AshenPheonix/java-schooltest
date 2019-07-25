package com.lambdaschool.school.service;


import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest {

    @Autowired private CourseService courseService;
    @Autowired private InstructorService instructorService;

    @Before
    public void AsetUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void BtearDown() throws Exception {
    }

    @Test
    public void CfindAll() {
        //assertEquals(6, courseService.findAll().size());
    }

    @Test
    public void DgetCountStudentsInCourse() {
        assertEquals(5,courseService.getCountStudentsInCourse().size());
    }

//    @Test
//    public void Edelete() {
//        courseService.delete(5);
//        assertEquals(5,courseService.findAll().size());
//    }
//
//    @Test(expected = EntityNotFoundException.class)
//    public void FdeleteFailed(){
//        courseService.delete(100);
//        assertEquals(4,courseService.findAll().size());
//    }

    @Test
    public void GfindCourseById() {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test
    public void HtestSave() throws Exception {
        Course temp=new Course();
        temp.setCoursename("Pot");

        Course added=courseService.save(temp);
        assertNotNull(added);
        Course found=courseService.findCourseById(added.getCourseid());
        assertEquals(found.getCoursename(),added.getCoursename());
    }
}