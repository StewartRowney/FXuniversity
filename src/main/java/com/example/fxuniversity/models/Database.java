package com.example.fxuniversity.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Database {
    public static HashMap<UUID,Student> studentHashMap = new HashMap<>();
    public static HashMap<UUID,Course> courseHashMap = new HashMap<>();
    public static HashMap<UUID, Professor> professorHashMap = new HashMap<>();
    public static HashMap<UUID, Class> classHashMap = new HashMap<>();
    public static HashMap<UUID, Transcript> transcriptHashMap = new HashMap<>();
    public static HashMap<UUID, Department> departmentHashMap = new HashMap<>();
    public static ArrayList<CourseClassRelationship> courseClassRelationshipArrayList = new ArrayList<>();
    public static ArrayList<ProfessorClassRelationship> professorClassRelationshipArrayList = new ArrayList<>();
    public static ArrayList<StudentClassRelationship> studentClassRelationshipArrayList = new ArrayList<>();


    public static void setUpDatabase(){
        loadStudentData();
        loadProfessorData();
        loadClassData();
        loadCourseData();
    }
    private static void loadStudentData() {

        try (Scanner fileScanner = new Scanner(new File("src/main/resources/studentmockdata.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitter = line.split(",");
                Student student = new Student(splitter[0], splitter[1], splitter[2], splitter[3], splitter[4]);
                studentHashMap.put(student.getId(), student);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Hey, we couldn't find the file");
        }
    }
    private static void loadProfessorData() {

        try (Scanner fileScanner = new Scanner(new File("src/main/resources/professormockdata.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitter = line.split(",");

                Professor professor = new Professor(splitter[0], splitter[1], splitter[2], splitter[3],  UUID.randomUUID(), Integer.parseInt(splitter[5].trim()));
                professorHashMap.put(professor.getId(), professor);

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Hey, we couldn't find the file");
        }
    }
    private static void loadClassData() {

        try (Scanner fileScanner = new Scanner(new File("src/main/resources/classesmockdata.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitter = line.split(",");

                Class classroom = new Class(Integer.parseInt(splitter[0]), DayOfWeek.of(Integer.parseInt(splitter[1])), LocalTime.parse(splitter[2]), Duration.ofHours(Long.parseLong(splitter[3])), Double.parseDouble(splitter[4]));
                classHashMap.put(classroom.getId(), classroom);

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Hey, we couldn't find the file");
        }
    }
    private static void loadCourseData() {

        try (Scanner fileScanner = new Scanner(new File("src/main/resources/coursemockdata.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitter = line.split(",");

                Course course = new Course(splitter[0], splitter[1], splitter[2], "", splitter[3]);
                courseHashMap.put(course.getId(), course);

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Hey, we couldn't find the file");
        }
    }

    public static Student getStudent(UUID id) {
        return studentHashMap.get(id);
    }
    public static void addNewStudent(Student student) {
        studentHashMap.put(student.getId(), student);
    }

    public static Course getCourse(UUID id) {
        return courseHashMap.get(id);
    }
    public static void addNewCourse(Course course) {
        courseHashMap.put(course.getId(), course);
    }

    public static Collection<Class> getAllClassesInCourse(UUID courseId) {
        ArrayList<UUID> classIds = new ArrayList<>();
        for (CourseClassRelationship ccr : courseClassRelationshipArrayList) {
            if (ccr.getCourseID() == courseId) {
                classIds.addAll(ccr.getClassIDs());
                break;
            }
        }

        ArrayList<Class> classes = new ArrayList<>();
        for (UUID id : classIds) {
            classes.add(classHashMap.get(id));
        }

        return classes;
    }

    public static Collection<Class> getAllClassesForProfessor(UUID professorId) {
        ArrayList<UUID> classIds = new ArrayList<>();
        for (ProfessorClassRelationship pcr : professorClassRelationshipArrayList) {
            if (pcr.getProfessorID() == professorId) {
                classIds.add(pcr.getClassID());
            }
        }

        ArrayList<Class> classes = new ArrayList<>();
        for (UUID id : classIds) {
            classes.add(classHashMap.get(id));
        }

        return classes;
    }

    public static Collection<Class> getAllProfessorClassesForDay(UUID professorId, DayOfWeek day) {
        Collection<Class> classes = Database.getAllClassesForProfessor(professorId);
        Collection<Class> relevantClasses = new ArrayList<>();
        for (Class currentClass: classes) {
            if (currentClass.getDay() == day) {
                relevantClasses.add(currentClass);
            }
        }
        return relevantClasses;
    }
}
