public class Person {
    private String firstName;
    private String secondName;
    private int age;
    private boolean licenceActive = true;

    public Person(String firstName, String secondName, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public boolean isLicenceActive() {
        return licenceActive;
    }

    public void setLicenceIsActive(boolean active) {
        licenceActive = active;
    }
}
