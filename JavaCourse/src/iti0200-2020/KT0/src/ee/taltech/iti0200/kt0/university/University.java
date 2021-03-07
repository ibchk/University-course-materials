import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class University {
    private String name;
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Course> finishedCourses = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public boolean addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    public Optional<Course> createCourse(String name, int eap) {
        for (Course course : courses) {
            if (course.getName() == name) {
                return Optional.empty();
            }
        }
        if (eap > 0 || name.isBlank() || name.isEmpty()) {
            Course newCourse = new Course(this, name, eap);
            courses.add(newCourse);
            return Optional.of(newCourse);
        }
        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> getFinishedCourses() {
        return finishedCourses;
    }

    public void finishCourse(Course course) {
        finishedCourses.add(course);
    }

    public List<Student> getStudentsOrderedByResults() {
        List<Student> copy = new ArrayList<>(this.students);

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < this.students.size(); i++) {
            Student best = null;
            for (Student student : copy) {
                if (best == null) {
                    best = student;
                } else if (student.getEap() > best.getEap()) {
                    best = student;
                } else if (student.getEap() == best.getEap()) {
                    if (student.getCourses().size() > best.getCourses().size()) {
                        best = student;
                    }
                }
            }
            students.add(best);
            copy.remove(best);
        }
        return students;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = new int[a.length];
        for (int i = 1, j = b.length / 2 -1; i < a.length; i++, j--){
            b[a.length - 1] = a[b.length / 2 + j] + a[a.length/2 - i + 1];
        }
        System.out.println(b[2]);
    }
}
