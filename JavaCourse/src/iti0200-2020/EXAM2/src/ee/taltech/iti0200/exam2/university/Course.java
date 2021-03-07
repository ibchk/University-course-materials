import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Course {

    private String name;
    private Teacher teacher;
    private GradingType gradingType;
    private Integer eap;
    private CourseType courseType;
    private boolean allStudentsMarked = false;
    private List<Student> students = new LinkedList<>();
    private University university;
    private Map<Student, String> courseFinishedStudents = new HashMap<>();

    public Course(String name, University university, Teacher teacher, Integer eap,
                  GradingType gradingType, CourseType courseType) {
        this.name = name;
        this.teacher = teacher;
        this.eap = eap;
        this.gradingType = gradingType;
        this.courseType = courseType;
        this.university = university;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void putMarks(Map<Student, String> students) {
        for (Student student : this.students) {
            if (students.containsKey(student)) {
                if (gradingType.equals(GradingType.MARK)) {
                    if (students.get(student).equals("0") || students.get(student).equals("1")
                            || students.get(student).equals("2") || students.get(student).equals("3")
                            || students.get(student).equals("4") || students.get(student).equals("5")) {
                        courseFinishedStudents.put(student, students.get(student));
                        student.finishCourse(this, students.get(student));
                        students.remove(student);
                    }
                } else if (gradingType.equals(GradingType.PASSFAIL)) {
                    if (students.get(student).toUpperCase().equals("MA") || students.get(student)
                            .toUpperCase().equals("A")) {
                        courseFinishedStudents.put(student, students.get(student).toUpperCase());
                        student.finishCourse(this, students.get(student).toUpperCase());
                        students.remove(student);
                    }
                }
            }
        }
        if (students.size() == 0) {
            allStudentsMarked = true;
            teacher.finishCourse(this);
            university.finishCourse(this);
        }
    }

    public Integer getEap() {
        return eap;
    }

    public void addStudent(Student student) {
        if (!allStudentsMarked) {
            students.add(student);
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Map<Student, String> getCourseFinishedStudents() {
        return courseFinishedStudents;
    }
}
