import java.util.LinkedList;
import java.util.List;

public class University {

    static final int ID100000 = 100000;
    static final int AGEMIN15 = 15;

    private int nextStudentId = ID100000;
    private String name;
    private String location;
    private List<Teacher> teachers = new LinkedList<>();
    private List<Student> students = new LinkedList<>();
    private List<Student> finishedStudyStudents = new LinkedList<>();
    private List<StudyProgramme> studyProgrammes = new LinkedList<>();
    private List<Course> courses = new LinkedList<>();
    private List<Course> finishedCourses = new LinkedList<>();

    public University(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public List<Student> getAllStudents() {
        List<Student> all = new LinkedList<>(students);
        all.addAll(finishedStudyStudents);
        return all;
    }

    public List<Student> getStudyingStudents() {
        return students;
    }

    public List<Student> getFinishedStudyStudents() {
        return finishedStudyStudents;
    }

    public void addFinishedStudent(Student student) {
        if (!finishedStudyStudents.contains(student)) {
            finishedStudyStudents.add(student);
            students.remove(student);
        }
    }

    public boolean addStudentToTheUniversity(Student student, StudyProgramme studyProgramme) {
        if (!students.contains(student) && student.getUniversity().isEmpty() && student.getAge() > AGEMIN15
                && studyProgrammes.contains(studyProgramme)) {
            students.add(student);
            student.setUniversity(this);
            student.setStudyProgramme(studyProgramme);
            studyProgramme.addStudent(student);
            nextStudentId++;
            student.setStudentID(studyProgramme.getName() + nextStudentId);
            student.setStudying(true);
            return true;
        }
        return false;
    }

    public boolean addStudyProgrammeToUniversity(StudyProgramme studyProgramme) {
        if (!studyProgrammes.contains(studyProgramme) && studyProgramme.getCurrentEAP()
                .equals(studyProgramme.getMaxEAP()) && studyProgramme.getMaxEAP() > 0) {
            for (Course course : studyProgramme.getCourses()) {
                if (!courses.contains(course)) {
                    return false;
                }
            }
            studyProgrammes.add(studyProgramme);
            return true;
        }
        return false;
    }

    public void addCourseToUniversity(Course course) {
        if (!courses.contains(course) && course.getEap() > 0) {
            if (!teachers.contains(course.getTeacher())) {
                teachers.add(course.getTeacher());
            }
            courses.add(course);
            course.getTeacher().addCourse(course);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void finishCourse(Course course) {
        courses.remove(course);
        finishedCourses.add(course);
    }
}
