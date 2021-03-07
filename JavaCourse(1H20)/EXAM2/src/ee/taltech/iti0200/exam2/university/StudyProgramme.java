import java.util.LinkedList;
import java.util.List;

public class StudyProgramme {

    private String name;
    private Integer maxEAP;
    private List<Course> courses = new LinkedList<>();
    private List<Student> students = new LinkedList<>();

    public StudyProgramme(String name, Integer maxEAP) {
        this.name = name;
        this.maxEAP = maxEAP;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean addCourse(List<Course> courses) {
        int sumEAP = 0;
        for (Course course : courses) {
            if (course.getEap() > 0) {
                sumEAP += course.getEap();
            }
        }
        if (maxEAP - getCurrentEAP() >= sumEAP) {
            for (Course course : courses) {
                if (!this.courses.contains(course) && course.getEap() > 0) {
                    this.courses.add(course);
                }
            }
            return true;
        }
        return false;
    }

    public boolean addCourse(Course course) {
        if (course.getEap() > 0 && course.getEap() + getCurrentEAP() <= maxEAP && !this.courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    public Integer getCurrentEAP() {
        int eap = 0;
        for (Course course : courses) {
            eap += course.getEap();
        }
        return eap;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxEAP() {
        return maxEAP;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
