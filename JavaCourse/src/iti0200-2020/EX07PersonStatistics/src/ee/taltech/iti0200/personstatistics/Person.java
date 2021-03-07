package ee.taltech.iti0200.personstatistics;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private double heightInMeters;
    private String occupation;
    private String nationality;
    private Gender gender;

    public Person(String firstName, String lastName, int age, Gender gender,
                  double heightInMeters, String occupation, String nationality) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightInMeters = heightInMeters;
        this.gender = gender;
        this.nationality = nationality;
        this.occupation = occupation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeightInMeters() {
        return heightInMeters;
    }

    public void setHeightInMeters(double heightInMeters) {
        this.heightInMeters = heightInMeters;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
