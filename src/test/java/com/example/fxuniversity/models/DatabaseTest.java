package com.example.fxuniversity.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @BeforeEach
    void beforeEach () {
        Database.setUpDatabase();
    }

    @Test
    void testGetStudent_ExpectSameStudent() {
        Student expected = new Student("test", "", "", "", "");
        Database.addNewStudent(expected);

        Student actual = Database.getStudent(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getProfessor() {
    }

    @Test
    void getAllClassesInCourse() {
    }

    @Test
    void getAllClassesForProfessor() {
    }

    @Test
    void getAllProfessorClassesForDay() {
    }

    @Test
    void getAllStudentsInClass() {
    }

    @Test
    void getAllStudentsInClassNotGradedYet() {
    }

    @Test
    void getAllCoursesAStudentIsNotAlreadyOn() {
    }

    @Test
    void getAllStudentTranscripts() {
    }

    @Test
    void getAllTranscriptsForClass() {
    }

    @Test
    void getAllCoursesFromDepartment() {
    }

    @Test
    void getAllCourseFromClass() {
    }

    @Test
    void getAllDepartments() {
    }

    @Test
    void getAllCourses() {
    }

    @Test
    void addNewStudent() {
    }

    @Test
    void addNewCourse() {
    }

    @Test
    void addNewClass() {
    }

    @Test
    void addStudentToClass() {
    }

    @Test
    void addNewTranscript() {
    }

    @Test
    void editClass() {
    }

    @Test
    void editCourse() {
    }

    @Test
    void removeClass() {
    }

    @Test
    void removeCourse() {
    }

    @Test
    void logIn() {
    }
}