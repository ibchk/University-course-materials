import java.util.LinkedList;
import java.util.List;

public class Declaration {

    static final int EAPMAX = 45;

    private boolean declared = false;
    private Student student;
    private int maxEAP = EAPMAX;
    private List<Course> coursesToDeclare = new LinkedList<>();

    public Declaration(Student student) {
        this.student = student;
    }

    public List<Course> getCoursesToDeclare() {
        return coursesToDeclare;
    }

    public void addCourseToDeclare(Course course) {
        if (!coursesToDeclare.contains(course) && calculateCurrentEAP() + course.getEap() <= maxEAP) {
            coursesToDeclare.add(course);
        }
    }

    public boolean removeCourseFromDeclaration(Course course) {
        if (coursesToDeclare.contains(course)) {
            coursesToDeclare.remove(course);
            return true;
        }
        return false;
    }

    public int calculateCurrentEAP() {
        int eap = 0;
        for (Course course : coursesToDeclare) {
            eap += course.getEap();
        }
        return eap;
    }
}
