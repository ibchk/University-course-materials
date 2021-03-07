import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name = "";
    private int eap = 0;
    private List<Course> courses = new ArrayList<>();
    private List<Course> finishedCourses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEap() {
        return eap;
    }

    public String toString() {
        return name + " (" + eap + ")";
    }

    public void addEap(int eap, Course course) {
        this.eap += eap;
        courses.remove(course);
        finishedCourses.add(course);
    }

    public void addToCourse(Course course){
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
