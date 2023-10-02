package com.example.fxuniversity.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Database {
    static HashMap<UUID,Student> studentHashMap = new HashMap<>();
    static HashMap<UUID,Course> courseHashMap = new HashMap<>();
    static HashMap<UUID, Professor> professorHashMap = new HashMap<>();
    static HashMap<UUID, Class> classHashMap = new HashMap<>();
    static HashMap<UUID, Transcript> transcriptHashMap = new HashMap<>();
    static HashMap<UUID, Department> departmentHashMap = new HashMap<>();
    static ArrayList<CourseClassRelationship> courseClassRelationshipArrayList = new ArrayList<>();
    static ArrayList<ProfessorClassRelationship> professorClassRelationshipArrayList = new ArrayList<>();
    static ArrayList<StudentClassRelationship> studentClassRelationshipArrayList = new ArrayList<>();

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
                System.out.println(professor.getName());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Hey, we couldn't find the file");
        }
    }

    private static void loadClassData() {
        String pattern = "dd-MM-yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        try (Scanner fileScanner = new Scanner(new File("src/main/resources/classmockdata.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitter = line.split(",");

                Class classroom = new Class(Integer.parseInt(splitter[0]), LocalDateTime.parse(splitter[1],formatter), Double.parseDouble(splitter[2]));
                classHashMap.put(classroom.getId(), classroom);
                System.out.println(classroom.getSemester());
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
                System.out.println(course.getName());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Hey, we couldn't find the file");
        }
    }
}
