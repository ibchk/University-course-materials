import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Course {

    private String name;
    private University uni;
    private int eap;
    private List<Student> students = new ArrayList<>();
    private boolean courseIsStillGoing;

    public Course(University uni, String name, int eap) {
        this.eap = eap;
        this.name = name;
        courseIsStillGoing = true;
        this.uni = uni;
    }

    public boolean addStudent(Student student) {
        if (!students.contains(student) && uni.getStudents().contains(student)) {
            students.add(student);
            student.addToCourse(this);
            return true;
        }
        return false;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean finish() {
        if (courseIsStillGoing) {
            for (Student student : students) {
                student.addEap(eap, this);
            }
            courseIsStillGoing = false;
            uni.finishCourse(this);
            return true;
        }
        return false;
    }

    public boolean isFinished(){
        return !courseIsStillGoing;
    }

    public String toString(){
        return uni.getName() + ": " + name + " (" + eap + ")";
    }

    public String getName() {
        return name;
    }
}
