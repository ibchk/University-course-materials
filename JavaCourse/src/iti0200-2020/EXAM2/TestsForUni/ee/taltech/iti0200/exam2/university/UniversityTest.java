import java.util.Optional;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {

    static final int AGE19 = 19;
    static final int AGE23 = 23;
    static final int AGE34 = 34;
    static final int AGE55 = 55;
    static final int AGE18 = 18;
    static final int AGE21 = 21;
    static final int AGE17 = 17;
    static final int AGE35 = 35;
    static final int EAP10 = 10;
    static final int EAP20 = 20;
    static final int EAP9 = 9;
    static final int EAP69 = 69;
    static final int EAP60 = 60;
    static final int EAP40 = 40;
    static final int LISTSIZE7 = 7;
    static final int LISTSIZE6 = 6;

    // Loome kaks ülikooli
    University ttu = new University("TTU", "Tallinn");

    // Loome tudengid
    Student mart = new Student("Mart", AGE19);
    Student anna = new Student("Anna", AGE23);
    Student rick = new Student("Rick", AGE34);
    Student robin = new Student("Robin", AGE55);
    Student keit = new Student("Keit", AGE17);
    Student artur = new Student("Artur", AGE18);
    Student nicolas = new Student("Nicolas", AGE21);

    // Loome õpetajad
    Teacher ago = new Teacher("Ago", AGE35, ttu);
    Teacher rain = new Teacher("Rain", AGE35, ttu);
    Teacher evelin = new Teacher("Evelin", AGE35, ttu);

    // Loome kursused, anname kaasa hindamissüsteemi, kursusetüübi ja õpetaja.
    Course math1 = new Course("math", ttu, rain, EAP10, GradingType.MARK, CourseType.COMMON);
    Course java = new Course("java", ttu, evelin, EAP20, GradingType.MARK, CourseType.SPECIALITY);
    Course python = new Course("python", ttu, ago, EAP10, GradingType.PASSFAIL, CourseType.SPECIALITY);
    Course computeScience = new Course("computer science", ttu, ago, EAP10, GradingType.MARK,
            CourseType.SPECIALITY);
    Course math2 = new Course("math", ttu, rain, EAP10, GradingType.PASSFAIL, CourseType.FREE);
    Course math3 = new Course("math", ttu, rain, EAP9, GradingType.MARK, CourseType.FREE);
    Course math4 = new Course("math", ttu, rain, EAP10, GradingType.MARK, CourseType.FREE);

    // Loome vidase kursuse ja proovime lisada selle ülikooli
    @org.junit.jupiter.api.Test
    void addWrongCourses() {
        Course math5 = new Course("math", ttu, rain, -5, GradingType.MARK, CourseType.FREE);
        Course math6 = new Course("math", ttu, rain, 0, GradingType.MARK, CourseType.FREE);
        ttu.addCourseToUniversity(math5);
        ttu.addCourseToUniversity(math6);
        assertEquals(ttu.getCourses().size(), 0);
    }

    @org.junit.jupiter.api.Test
    void testTeacherAndStudentAndUniOnCorrectMade() {
        // Siin kontrollime kas getterid ja setterid nimede,vanuse ja id jaoks on õiged
        ago.setAge(AGE19);
        assertEquals(ago.getAge(), AGE19);
        ago.setName("Mark");
        assertEquals(ago.getName(), "Mark");
        assertEquals(ago.getOngoingCourses().size(), 0);
        mart.setName("Martin");
        mart.setAge(AGE55);
        assertEquals(mart.getName(), "Martin");
        assertEquals(mart.getAge(), AGE55);
        assertEquals(mart.getStudentID(), Optional.empty());
        assertFalse(mart.isStudying());
        ttu.setName("Maaulikool");
        assertEquals(ttu.getName(), "Maaulikool");
        assertEquals(ttu.getLocation(), "Tallinn");
    }

    @org.junit.jupiter.api.Test
    void makeStudyProgramme() {
        // Lisame kursused ülikooli, ilma selleta ei saa neid kasutada.
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);

        // Koostame õppekava, anname kaasa EAP, ideaalis peab see olema kas 120 (magistri õpekava) või
        // 180 (bakalaureus).
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);

        // Lisame aineid, mis ületavad ainekava EAPsid
        List<Course> iaibLi1 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math4));
        assertFalse(iaib.addCourse(iaibLi1));

        // Lisame aineid, mis ei ületa ainekava EAPsid (9 EAPd jääb puudu). Aineid saab lisada nii listina,
        // kui ka ühekaupa.
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2));
        assertTrue(iaib.addCourse(iaibLi2));
        assertEquals(iaib.getCurrentEAP(), EAP60);

        // Proovime lisada antud ainekava ülikooli.
        assertFalse(ttu.addStudyProgrammeToUniversity(iaib)); // -> false, kuna kursuseid on vähem kui õppekava
        // EAP-d peab olema.

        // Lisame ainekavasse aine, mis kataks vajalikud EAP-d. Kui proovime lisada aine mis ületab need
        // vajalikud EAP-d, siis ta ei lisandu.
        assertTrue(iaib.addCourse(math3)); // -> true
    }

    @org.junit.jupiter.api.Test
    void addStudyProgramme() {
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math3));
        iaib.addCourse(iaibLi2);

        // Kui me lisame tudengi koos ainekavaga õppima ülikooli, siis meil ei tule see välja, kuna
        // kui ainekava pole ülikoolis, siis seda deklareerida ei saa.
        assertFalse(ttu.addStudentToTheUniversity(nicolas, iaib));
        assertEquals(ttu.getAllStudents().size(), 0);

        // Lisame ainekava ülikooli
        ttu.addStudyProgrammeToUniversity(iaib);

        // Nüüd saab lisada tudengeid koos selle õppekavaga ülikooli.
        ttu.addStudentToTheUniversity(nicolas, iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        assertEquals(ttu.getAllStudents().size(), 1);
    }

    @org.junit.jupiter.api.Test
    void tryToaddStudentToAnotherUni() {
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math3));
        iaib.addCourse(iaibLi2);
        ttu.addStudyProgrammeToUniversity(iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        University tu = new University("TU", "Tartu");
        Teacher ago2 = new Teacher("Ago", AGE55, tu);
        Teacher rain2 = new Teacher("Rain", AGE55, tu);
        Teacher evelin2 = new Teacher("Evelin", AGE55, tu);
        Course course1 = new Course("math", tu, ago2, EAP10, GradingType.MARK, CourseType.COMMON);
        Course course2 = new Course("java", tu, rain2, EAP20, GradingType.MARK, CourseType.SPECIALITY);
        Course course3 = new Course("python", tu, evelin2, EAP10, GradingType.PASSFAIL, CourseType.SPECIALITY);
        tu.addCourseToUniversity(course1);
        tu.addCourseToUniversity(course2);
        tu.addCourseToUniversity(course3);
        StudyProgramme iaib2 = new StudyProgramme("IAIB", EAP40);
        List<Course> tuCourses = new LinkedList<>(Arrays.asList(course1, course2, course3));
        iaib2.addCourse(tuCourses);
        iaib2.getCurrentEAP();
        tu.addStudyProgrammeToUniversity(iaib2);
        assertFalse(ttu.addStudentToTheUniversity(nicolas, iaib2));
    }

    @org.junit.jupiter.api.Test
    void declare() {
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math3));
        iaib.addCourse(iaibLi2);
        ttu.addStudyProgrammeToUniversity(iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        ttu.addStudentToTheUniversity(artur, iaib);
        ttu.addStudentToTheUniversity(keit, iaib);
        ttu.addStudentToTheUniversity(robin, iaib);
        ttu.addStudentToTheUniversity(rick, iaib);
        ttu.addStudentToTheUniversity(anna, iaib);
        ttu.addStudentToTheUniversity(mart, iaib);
        assertEquals(mart.getStudyProgramme().get(), iaib);
        assertEquals(mart.getStudentID().get(), "IAIB100007");
        mart.startDeclaration();
        anna.startDeclaration();
        rick.startDeclaration();
        robin.startDeclaration();
        keit.startDeclaration();
        artur.startDeclaration();
        nicolas.startDeclaration();

        // lisame aineid, neid peab olema vähemalt 15 EAP, max on 45 EAP
        mart.addCourseToDeclaration(math1);
        assertFalse(mart.finishAndSendDeclaration());
        mart.addCourseToDeclaration(math1);
        assertFalse(mart.finishAndSendDeclaration());
        mart.addCourseToDeclaration(java);
        assertTrue(mart.finishAndSendDeclaration());
        anna.addCourseToDeclaration(java);
        anna.addCourseToDeclaration(java);
        assertTrue(anna.finishAndSendDeclaration());
        rick.addCourseToDeclaration(java);
        rick.addCourseToDeclaration(python);
        rick.finishAndSendDeclaration();
        robin.addCourseToDeclaration(java);
        robin.addCourseToDeclaration(math2);
        robin.removeCourseFromDeclaration(math2);
        robin.addCourseToDeclaration(python);
        robin.finishAndSendDeclaration();
        keit.addCourseToDeclaration(java);
        keit.addCourseToDeclaration(python);
        keit.finishAndSendDeclaration();
        artur.addCourseToDeclaration(java);
        artur.addCourseToDeclaration(python);
        artur.finishAndSendDeclaration();
        nicolas.addCourseToDeclaration(java);
        nicolas.addCourseToDeclaration(python);
        nicolas.finishAndSendDeclaration();
        assertEquals(java.getStudents().size(), LISTSIZE7);
    }

    @org.junit.jupiter.api.Test
    void finishCourseJavaMarks() {
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math3));
        iaib.addCourse(iaibLi2);
        ttu.addStudyProgrammeToUniversity(iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        ttu.addStudentToTheUniversity(artur, iaib);
        ttu.addStudentToTheUniversity(keit, iaib);
        ttu.addStudentToTheUniversity(robin, iaib);
        ttu.addStudentToTheUniversity(rick, iaib);
        ttu.addStudentToTheUniversity(anna, iaib);
        ttu.addStudentToTheUniversity(mart, iaib);
        mart.startDeclaration();
        anna.startDeclaration();
        rick.startDeclaration();
        robin.startDeclaration();
        keit.startDeclaration();
        artur.startDeclaration();
        nicolas.startDeclaration();
        mart.addCourseToDeclaration(math1);
        mart.addCourseToDeclaration(math1);
        mart.addCourseToDeclaration(java);
        mart.finishAndSendDeclaration();
        anna.addCourseToDeclaration(java);
        anna.addCourseToDeclaration(java);
        anna.finishAndSendDeclaration();
        rick.addCourseToDeclaration(java);
        rick.addCourseToDeclaration(python);
        rick.finishAndSendDeclaration();
        robin.addCourseToDeclaration(java);
        robin.addCourseToDeclaration(python);
        robin.finishAndSendDeclaration();
        keit.addCourseToDeclaration(java);
        keit.addCourseToDeclaration(python);
        keit.finishAndSendDeclaration();
        artur.addCourseToDeclaration(java);
        artur.addCourseToDeclaration(python);
        artur.finishAndSendDeclaration();
        nicolas.addCourseToDeclaration(java);
        nicolas.addCourseToDeclaration(python);
        nicolas.finishAndSendDeclaration();
        assertEquals(java.getCourseFinishedStudents().size(), 0);
        Map<Student, String> marks1 = new HashMap<>();
        marks1.put(mart, "2");
        marks1.put(anna, "1");
        marks1.put(rick, "MA");
        marks1.put(robin, "4");
        marks1.put(keit, "5");
        marks1.put(artur, "6");
        marks1.put(nicolas, "0");
        // Artur ja rick ei saa kirja hinde, kuna on vaja numbrilist hinnet 0-5
        java.putMarks(marks1);
        // Siin kontrollime kas kursused on inimestel lõppenud
        assertEquals(java.getCourseFinishedStudents().size(), 5);
        assertEquals(mart.getPassedCourses().size(), 1);
        assertEquals(nicolas.getPassedCourses().size(), 0);
        assertEquals(nicolas.getFailedCourses().size(), 1);
    }

    @org.junit.jupiter.api.Test
    void finishCourseJavaPasses() {
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math3));
        iaib.addCourse(iaibLi2);
        ttu.addStudyProgrammeToUniversity(iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        ttu.addStudentToTheUniversity(artur, iaib);
        ttu.addStudentToTheUniversity(keit, iaib);
        ttu.addStudentToTheUniversity(robin, iaib);
        ttu.addStudentToTheUniversity(rick, iaib);
        ttu.addStudentToTheUniversity(anna, iaib);
        ttu.addStudentToTheUniversity(mart, iaib);
        mart.startDeclaration();
        anna.startDeclaration();
        rick.startDeclaration();
        robin.startDeclaration();
        keit.startDeclaration();
        artur.startDeclaration();
        nicolas.startDeclaration();
        mart.addCourseToDeclaration(math1);
        mart.addCourseToDeclaration(math1);
        mart.addCourseToDeclaration(java);
        mart.finishAndSendDeclaration();
        anna.addCourseToDeclaration(java);
        anna.addCourseToDeclaration(java);
        anna.finishAndSendDeclaration();
        rick.addCourseToDeclaration(java);
        rick.addCourseToDeclaration(python);
        rick.finishAndSendDeclaration();
        robin.addCourseToDeclaration(java);
        robin.addCourseToDeclaration(python);
        robin.finishAndSendDeclaration();
        keit.addCourseToDeclaration(java);
        keit.addCourseToDeclaration(python);
        keit.finishAndSendDeclaration();
        artur.addCourseToDeclaration(java);
        artur.addCourseToDeclaration(python);
        artur.finishAndSendDeclaration();
        nicolas.addCourseToDeclaration(java);
        nicolas.addCourseToDeclaration(python);
        nicolas.finishAndSendDeclaration();
        assertEquals(python.getCourseFinishedStudents().size(), 0);
        Map<Student, String> marks1 = new HashMap<>();
        marks1.put(rick, "A");
        marks1.put(robin, "a");
        marks1.put(keit, "MA");
        marks1.put(artur, "A");
        marks1.put(nicolas, "5");
        // Nicolas ei saa kirja hinde, kuna on vaja A või MA
        python.putMarks(marks1);
        // Siin kontrollime kas kursused on inimestel lõppenud
        assertEquals(python.getCourseFinishedStudents().size(), 4);
        assertEquals(keit.getPassedCourses().size(), 0);
        assertEquals(artur.getPassedCourses().size(), 1);
        assertEquals(keit.getFailedCourses().size(), 1);
        marks1.put(nicolas, "A");
        python.putMarks(marks1);
        assertEquals(python.getCourseFinishedStudents().size(), 5);
    }

    @org.junit.jupiter.api.Test
    void universityCheckStudents() {
        ttu.addCourseToUniversity(math1);
        ttu.addCourseToUniversity(java);
        ttu.addCourseToUniversity(python);
        ttu.addCourseToUniversity(computeScience);
        ttu.addCourseToUniversity(math2);
        ttu.addCourseToUniversity(math3);
        ttu.addCourseToUniversity(math4);
        StudyProgramme iaib = new StudyProgramme("IAIB", EAP69);
        List<Course> iaibLi2 = new LinkedList<>(Arrays.asList(math1, java, python, computeScience, math2, math3));
        iaib.addCourse(iaibLi2);
        ttu.addStudyProgrammeToUniversity(iaib);
        ttu.addStudentToTheUniversity(nicolas, iaib);
        ttu.addStudentToTheUniversity(artur, iaib);
        ttu.addStudentToTheUniversity(keit, iaib);
        ttu.addStudentToTheUniversity(robin, iaib);
        ttu.addStudentToTheUniversity(rick, iaib);
        ttu.addStudentToTheUniversity(anna, iaib);
        ttu.addStudentToTheUniversity(mart, iaib);
        mart.startDeclaration();
        anna.startDeclaration();
        rick.startDeclaration();
        robin.startDeclaration();
        keit.startDeclaration();
        artur.startDeclaration();
        nicolas.startDeclaration();
        mart.addCourseToDeclaration(math1);
        mart.addCourseToDeclaration(math1);
        mart.addCourseToDeclaration(java);
        mart.finishAndSendDeclaration();
        anna.addCourseToDeclaration(java);
        anna.addCourseToDeclaration(java);
        anna.finishAndSendDeclaration();
        rick.addCourseToDeclaration(java);
        rick.addCourseToDeclaration(python);
        rick.finishAndSendDeclaration();
        robin.addCourseToDeclaration(java);
        robin.addCourseToDeclaration(python);
        robin.finishAndSendDeclaration();
        keit.addCourseToDeclaration(java);
        keit.addCourseToDeclaration(python);
        keit.finishAndSendDeclaration();
        artur.addCourseToDeclaration(java);
        artur.addCourseToDeclaration(python);
        artur.finishAndSendDeclaration();
        nicolas.addCourseToDeclaration(java);
        nicolas.addCourseToDeclaration(python);
        nicolas.finishAndSendDeclaration();
        Map<Student, String> marks1 = new HashMap<>();
        marks1.put(rick, "A");
        marks1.put(robin, "A");
        marks1.put(keit, "MA");
        marks1.put(artur, "A");
        marks1.put(nicolas, "5");
        python.putMarks(marks1);
        marks1.put(nicolas, "A");
        python.putMarks(marks1);
        // Siin kontrollime kas kursused ja tudengid on õigetes kohtades ülikoolis kirjas
        assertEquals(ttu.getAllStudents().size(), LISTSIZE7);
        assertEquals(ttu.getStudyingStudents().size(), LISTSIZE7);
        ttu.addFinishedStudent(mart);
        assertEquals(ttu.getAllStudents().size(), LISTSIZE7);
        assertEquals(ttu.getStudyingStudents().size(), LISTSIZE6);
        assertEquals(ttu.getFinishedStudyStudents().size(), 1);
    }
}
