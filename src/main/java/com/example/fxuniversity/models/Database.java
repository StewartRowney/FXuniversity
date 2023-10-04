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
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Database {
    private static final HashMap<UUID,Student> studentHashMap = new HashMap<>();
    private static final HashMap<UUID,Course> courseHashMap = new HashMap<>();
    private static final HashMap<UUID, Professor> professorHashMap = new HashMap<>();
    private static final HashMap<UUID, Class> classHashMap = new HashMap<>();
    private static final HashMap<UUID, Transcript> transcriptHashMap = new HashMap<>();
    private static final HashMap<UUID, Department> departmentHashMap = new HashMap<>();
    private static final ArrayList<CourseClassRelationship> courseClassRelationshipArrayList = new ArrayList<>();
    private static final ArrayList<ProfessorClassRelationship> professorClassRelationshipArrayList = new ArrayList<>();
    private static final ArrayList<StudentClassRelationship> studentClassRelationshipArrayList = new ArrayList<>();


    public static void setUpDatabase(){
//        loadStudentData();
//        loadProfessorData();
//        loadClassData();
//        loadCourseData();
        populatePCR();
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

    public static void populatePCR() {
        Student student1 = new Student("Stewart","","","","");
        Student student2 = new Student("Thomas","","","","");
        Student student3 = new Student("Abhijeet","","","","");
        Student student4 = new Student("Jim","","","","");
        Student student5 = new Student("Tom","","","","");
        Student student6 = new Student("Bob","","","","");
        Student student7 = new Student("Jack","","","","");
        Student student8 = new Student("Hamish","","","","");
        Student student9 = new Student("Bert","","","","");

        Student[] students = {student1, student2, student3, student4, student5, student6, student7, student8, student9};
        for (Student s: students) {
            studentHashMap.put(s.getId(), s);
        }

        Class class1 = new Class(1, DayOfWeek.MONDAY, LocalTime.of(12,0,0), Duration.of(1, ChronoUnit.HOURS), 1.08 );
        Class class2 = new Class(1, DayOfWeek.TUESDAY, LocalTime.of(12,0,0), Duration.of(1, ChronoUnit.HOURS), 1.08 );
        Class class3 = new Class(1, DayOfWeek.MONDAY, LocalTime.of(14,0,0), Duration.of(1, ChronoUnit.HOURS), 1.08 );
        Class class4 = new Class(1, DayOfWeek.MONDAY, LocalTime.of(15,0,0), Duration.of(1, ChronoUnit.HOURS), 5.08 );
        Class[] classes = {class1, class2, class3, class4};
        for (Class c: classes) {
            classHashMap.put(c.getId(), c);
        }
        
        Department department1 = new Department("Physics");
        Department department2 = new Department("Maths");
        Department[] departments = {department1, department2};
        for (Department d: departments) {
            departmentHashMap.put(d.getId(), d);
        }

        Professor prof1 = new Professor("Ayush", "Aysuh12", "123", "pn@pn", department1.getId(), 69);
        Professor prof2 = new Professor("Bill", "Aysuh124", "12354", "pn@pn45", department2.getId(), 67);
        Professor prof3 = new Professor("Dave", "Aysuh125", "1234", "pn@ph", department2.getId(), 68);
        Professor[] professors = {prof1, prof2, prof3};
        for (Professor p: professors) {
            professorHashMap.put(p.getId(), p);
        }
        
        Course course1 = new Course("Legit Course", "super legit", "None", "", "ENG109");
        Course course2 = new Course("Empty Course", "you do nothing", "None", "", "ENG105");
        Course course3 = new Course("Another Course", "course", "None", "", "ENG100");
        Course[] courses = {course1, course2, course3};
        for (Course c: courses) {
            courseHashMap.put(c.getId(), c);
        }
        
        department1.addCourse(course1.getId());
        department2.addCourse(course2.getId());
        department2.addCourse(course3.getId());
        
        department1.addProfessors(prof1.getId());
        department2.addProfessors(prof2.getId());
        department2.addProfessors(prof3.getId());

        ArrayList<UUID> classIds = new ArrayList<>();
        classIds.add(class1.getId());
        classIds.add(class2.getId());
        courseClassRelationshipArrayList.add(new CourseClassRelationship(course1.getId(), classIds));
        ArrayList<UUID> course2Ids = new ArrayList<>();
        course2Ids.add(class3.getId());
        courseClassRelationshipArrayList.add(new CourseClassRelationship(course2.getId(), course2Ids));
        ArrayList<UUID> course3Ids = new ArrayList<>();
        course3Ids.add(class4.getId());
        courseClassRelationshipArrayList.add(new CourseClassRelationship(course3.getId(), course3Ids));

        professorClassRelationshipArrayList.add(new ProfessorClassRelationship(prof1.getId(), class1.getId()));
        professorClassRelationshipArrayList.add(new ProfessorClassRelationship(prof1.getId(), class2.getId()));
        professorClassRelationshipArrayList.add(new ProfessorClassRelationship(prof2.getId(), class3.getId()));
        professorClassRelationshipArrayList.add(new ProfessorClassRelationship(prof3.getId(), class4.getId()));

        studentClassRelationshipArrayList.add(new StudentClassRelationship(student1.getId(), class1.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student2.getId(), class1.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student3.getId(), class1.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student4.getId(), class1.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student5.getId(), class1.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student6.getId(), class1.getId()));

        studentClassRelationshipArrayList.add(new StudentClassRelationship(student7.getId(), class2.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student8.getId(), class2.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student9.getId(), class2.getId()));

        studentClassRelationshipArrayList.add(new StudentClassRelationship(student1.getId(), class4.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student2.getId(), class3.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student3.getId(), class3.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student4.getId(), class3.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student5.getId(), class4.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student6.getId(), class4.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student7.getId(), class3.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student8.getId(), class4.getId()));
        studentClassRelationshipArrayList.add(new StudentClassRelationship(student9.getId(), class3.getId()));

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

    public static HashMap<UUID, Student> getStudentHashMap() {
        return studentHashMap;
    }

    public static HashMap<UUID, Course> getCourseHashMap() {
        return courseHashMap;
    }

    public static HashMap<UUID, Professor> getProfessorHashMap() {
        return professorHashMap;
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

    public static void addStudentToClass(StudentClassRelationship src) {
        studentClassRelationshipArrayList.add(src);
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

    public static Collection<Student> getAllStudentsInClass(UUID classId) {
        ArrayList<UUID> studentIds = new ArrayList<>();
        for (StudentClassRelationship scr : studentClassRelationshipArrayList) {
            if (scr.getClassID() == classId) {
                studentIds.add(scr.getStudentID());
            }
        }

        ArrayList<Student> students = new ArrayList<>();
        for (UUID id : studentIds) {
            students.add(studentHashMap.get(id));
        }

        return students;
    }

    public static Collection<Course> getAllCoursesAStudentIsNotAlreadyOn(UUID studentId) {
        ArrayList<UUID> listOfClassIdsStudentIsOn = new ArrayList<>();
        for (StudentClassRelationship src: studentClassRelationshipArrayList) {
            if (src.getStudentID() == studentId) {
                listOfClassIdsStudentIsOn.add(src.getClassID());
            }
        }

        ArrayList<UUID> listOfCourseIdsStudentIsOn = new ArrayList<>();
        for (UUID classId : listOfClassIdsStudentIsOn) {
            for (CourseClassRelationship ccr: courseClassRelationshipArrayList) {
                if (ccr.getClassIDs().contains(classId)) {
                    listOfCourseIdsStudentIsOn.add(ccr.getCourseID());
                    break;
                }
            }
        }

        ArrayList<Course> coursesStudentIsNot = new ArrayList<>();
        for (Course course : courseHashMap.values()){
            if (!listOfCourseIdsStudentIsOn.contains(course.getId())) {
                coursesStudentIsNot.add(course);
            }
        }
        return coursesStudentIsNot;
    }

    public static void addNewTranscript(Transcript transcript) {
        transcriptHashMap.put(transcript.getId(), transcript);
    }

    public static Collection<Transcript> getAllStudentTranscripts(UUID studentId) {
        ArrayList<Transcript> transcripts = new ArrayList<>();
        for (Transcript t: transcriptHashMap.values()) {
            if (t.getStudentID() == studentId) {
                transcripts.add(t);
            }
        }
        return transcripts;
    }

    public static Collection<Transcript> getAllTranscriptsForClass(UUID classId) {
        ArrayList<Transcript> transcripts = new ArrayList<>();
        for (Transcript t: transcriptHashMap.values()) {
            if (t.getClassID() == classId) {
                transcripts.add(t);
            }
        }
        return transcripts;
    }
}
