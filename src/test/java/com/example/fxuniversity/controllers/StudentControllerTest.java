package com.example.fxuniversity.controllers;

import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Class;
import com.example.fxuniversity.models.relationships.StudentClassRelationship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    Department testDepartment;
    Course testCourse;
    Class classToAdd, classToAdd2;
    Professor prof;
    Student student;

    @BeforeEach
    void beforeEach () {
        Database.setUpDatabase();
        testDepartment = new Department("Test");
        testCourse = new Course("Test Course", "", "", "", "");
        classToAdd = new Class(1, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        classToAdd2 = new Class(2, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        prof = new Professor("TestProf", "", "", "test@professor.com", UUID.randomUUID(), 3);
        student = new Student("Test Student", "", "", "test@student.com","");
        Database.setUpDatabase();
        Database.logIn("tom@student.com");
        setUpStudentController();
    }
    @Test
    void onAddStudentToClass() {
        StudentClassRelationship src = new StudentClassRelationship(student.getId(), classToAdd.getId());
        Database.addStudentToClass(src);
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