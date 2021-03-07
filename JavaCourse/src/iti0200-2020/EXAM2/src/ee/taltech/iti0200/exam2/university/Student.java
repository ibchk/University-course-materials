import java.util.Optional;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class Student {

    static final int EAPMIN = 15;

    private String name;
    private String studentID;
    private int age;
    private University university;
    private StudyProgramme studyProgramme;
    private boolean studying = false;
    private boolean declaring = false;
    private boolean coursesGoing = false;
    private Declaration declaration;
    private Map<Course, String> finishedCourses = new HashMap<>();
    private List<Course> ongoingCourses = new LinkedList<>();

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Course> getPassedCourses() {
        List<Course> answer = new LinkedList<>();
        for (Map.Entry<Course, String> entry : finishedCourses.entrySet()) {
            if (!entry.getValue().equals("0") && !entry.getValue().equals("MA")) {
                answer.add(entry.getKey());
            }
        }
        return answer;
    }

    public List<Course> getFailedCourses() {
        List<Course> answer = new LinkedList<>();
        for (Map.Entry<Course, String> entry : finishedCourses.entrySet()) {
            if (entry.getValue().equals("0") || entry.getValue().equals("MA")) {
                answer.add(entry.getKey());
            }
        }
        return answer;
    }

    public boolean startDeclaration() {
        if (studying && !declaring && !coursesGoing) {
            declaring = true;
            declaration = new Declaration(this);
            return true;
        }
        return false;
    }

    public boolean addCourseToDeclaration(Course course) {
        if (declaring && university.getCourses().contains(course)
                && !ongoingCourses.contains(course) && !finishedCourses.containsKey(course)) {
            declaration.addCourseToDeclare(course);
            return true;
        }
        return false;
    }

    public boolean removeCourseFromDeclaration(Course course) {
        if (declaring) {
            return declaration.removeCourseFromDeclaration(course);
        }
        return false;
    }

    public boolean finishAndSendDeclaration() {
        if (declaration.calculateCurrentEAP() > EAPMIN) {
            for (Course course : declaration.getCoursesToDeclare()) {
                ongoingCourses.add(course);
                course.addStudent(this);
            }
            coursesGoing = true;
            declaring = false;
            return true;
        }
        return false;
    }

    public Optional<University> getUniversity() {
        if (!(university == null)) {
            return Optional.of(university);
        }
        return Optional.empty();
    }

    public Optional<StudyProgramme> getStudyProgramme() {
        if (!(studyProgramme == null)) {
            return Optional.of(studyProgramme);
        }
        return Optional.empty();
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setStudyProgramme(StudyProgramme studyProgramme) {
        this.studyProgramme = studyProgramme;
    }

    public Optional<String> getStudentID() {
        if (!(studentID == null)) {
            return Optional.of(studentID);
        }
        return Optional.empty();
    }

    public boolean isStudying() {
        return studying;
    }

    public void setStudying(boolean studying) {
        this.studying = studying;
    }

    public void finishCourse(Course course, String mark) {
        ongoingCourses.remove(course);
        finishedCourses.put(course, mark);
        if (ongoingCourses.size() == 0) {
            coursesGoing = false;
        }
    }

    public List<Course> getOngoingCourses() {
        return ongoingCourses;
    }
}
