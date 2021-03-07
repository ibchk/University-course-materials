import java.util.List;
import java.util.LinkedList;

public class Teacher {
    private String name;
    private int age;
    private University university;
    private List<Course> ongoingCourses = new LinkedList<>();
    private List<Course> finishedCourses = new LinkedList<>();

    public Teacher(String name, int age, University university) {
        this.university = university;
        this.name = name;
        this.age = age;
    }

    public void addCourse(Course course) {
        ongoingCourses.add(course);
    }

    public void finishCourse(Course course) {
        ongoingCourses.remove(course);
        finishedCourses.add(course);
    }

    public University getUniversity() {
        return university;
    }

    public List<Course> getOngoingCourses() {
        return ongoingCourses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
