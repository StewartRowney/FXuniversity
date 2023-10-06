package com.example.fxuniversity.models;

import com.example.fxuniversity.models.relationships.StudentClassRelationship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Department department;
    Course testCourse;

    Student student;
    Class classToAdd;
    Class classToAdd2;
    Professor prof;

    @BeforeEach
    void beforeEach() {
        Database.setUpDatabase();
        department = new Department("Test");
        testCourse = new Course("Test Course", "", "", "", "");
        classToAdd = new Class(1, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        classToAdd2 = new Class(2, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        prof = new Professor("TestProf", "", "", "test@professor.com", UUID.randomUUID(), 3);
        student = new Student("Test Student", "", "", "test@student.com","");
        //addEverythingToEverywhere();
    }

    void addEverythingToEverywhere() {
        Department department = new Department("Test");
        Database.addDepartment(department);
        Course testCourse = new Course("Test Course", "", "", "", "");
        Class classToAdd = new Class(1, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        Class classToAdd2 = new Class(2, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        Database.addNewCourse(testCourse, department.getId());
        Database.addNewClass(classToAdd, testCourse);
        Database.addNewClass(classToAdd2, testCourse);

        Database.addNewProfessor(prof);
        Database.addNewClass(classToAdd, testCourse);
        Database.addClassToAProfessor(prof.getId(), classToAdd.getId());
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
        Professor expected = new Professor("testProf", "", "", "professor@professor.com", UUID.randomUUID(), 1);
        Database.addNewProfessor(expected);

        Professor actual = Database.getProfessor(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void test_getAllClassesInCourse() {
        Department department = new Department("Test");
        Database.addDepartment(department);
        Course testCourse = new Course("Test Course", "", "", "", "");
        Class classToAdd = new Class(1, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        Class classToAdd2 = new Class(2, DayOfWeek.MONDAY, LocalTime.of(12, 12), Duration.of(2, ChronoUnit.HOURS), 1.09);
        Database.addNewCourse(testCourse, department.getId());
        ArrayList<Class> expected = new ArrayList<Class>();
        expected.add(classToAdd);
        expected.add(classToAdd2);
        Database.addNewClass(classToAdd, testCourse);
        Database.addNewClass(classToAdd2, testCourse);
        ArrayList<Class> actual = (ArrayList<Class>) Database.getAllClassesInCourse(testCourse.getId());
        assertEquals(expected, actual);

    }

    @Test
    void test_getAllClassesForProfessor() {
        Database.addNewProfessor(prof);
        Database.addNewClass(classToAdd, testCourse);
        Database.addClassToAProfessor(prof.getId(), classToAdd.getId());
        ArrayList<Class> expected = new ArrayList<>();
        expected.add(classToAdd);
        ArrayList<Class> actual = (ArrayList<Class>) Database.getAllClassesForProfessor(prof.getId());
        assertEquals(expected, actual);

    }

    @Test
    void test_getAllProfessorClassesForDay() {

        Database.addNewProfessor(prof);
        Database.addNewClass(classToAdd, testCourse);
        Database.addClassToAProfessor(prof.getId(), classToAdd.getId());

        ArrayList<Class> expected = new ArrayList<>();
        expected.add(classToAdd);

        ArrayList<Class> actual = (ArrayList<Class>) Database.getAllProfessorClassesForDay(prof.getId(), DayOfWeek.MONDAY);
        assertEquals(expected, actual);
    }

    @Test
    void test_getAllStudentsInClass() {

        Database.addNewStudent(student);
        StudentClassRelationship scr = new StudentClassRelationship(student.getId(), classToAdd.getId());
        Database.addStudentToClass(scr);
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(student);
        ArrayList<Student> actual = (ArrayList<Student>) Database.getAllStudentsInClass(classToAdd.getId());
        assertEquals(expected,actual);
    }

    @Test
    void test_getAllStudentsInClassNotGradedYet() {
        Student studentWithGrade = new Student("Graded Student", "", "", "", "");
        Database.addNewStudent(student);
        Database.addNewStudent(studentWithGrade);
        StudentClassRelationship scr = new StudentClassRelationship(student.getId(), classToAdd.getId());
        StudentClassRelationship scr2 = new StudentClassRelationship(studentWithGrade.getId(), classToAdd.getId());
        Database.addStudentToClass(scr);
        Database.addStudentToClass(scr2);
        Transcript gradedStudent = new Transcript(classToAdd.getId(), studentWithGrade.getId(), "A");
        Database.addNewTranscript(gradedStudent);
        Collection<Student> expected = new ArrayList<>();
        expected.add(student);
        Collection<Student> actual = Database.getAllStudentsInClassNotGradedYet(classToAdd.getId());
        assertEquals(expected, actual);

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