package com.example.fxuniversity.controllers;

import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    @BeforeEach
    void beforeEach () {
        Database.setUpDatabase();
        Database.logIn("tom@student.com");
        setUpStudentController();
    }
    @Test
    void onAddStudentToClass() {
        Student expected = new Student("test", "", "", "", "");
        //Database.addStudentToClass(expected);


    }

    @Test
    void onCourseSelection() {
    }

    @Test
    void onDepartmentSelection() {
    }

    @Test
    void onHome() {
    }

    @Test
    void onLogout() {
    }

    @Test
    void onRegisterForCourse() {
    }

    @Test
    void onSeeTranscripts() {
    }

    @Test
    void setUpStudentController() {
    }

    @Test
    void setUpCourseListForStudent() {
        Student expected = new Student("test","","","","");
        Collection<Course> expectedCourses = Database.getAllCoursesAStudentIsNotAlreadyOn(expected.getId());
        //Student actual = new Student(actual.)
       // assertEquals(expectedCourses, actual);
    }

    @Test
    void setUpDepartmentList() {
    }
}