package ee.taltech.iti0200.warehouses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkerTest {

    public static final int AGE24 = 24;
    public static final int AGE33 = 33;


    @Test
    void getWorkerSummary() {
        String firstName = "Mark";
        String lastName = "Mägi";
        String idCode = "40312132226";
        int age = AGE24;
        Worker newWorker = new Worker(firstName, lastName, idCode, age);
        assertEquals((firstName + " " + lastName + ", " + age + " (" + idCode + ")"), newWorker.getWorkerSummary());
    }

    @Test
    void setFirstName() {
        String firstName = "Mark";
        String lastName = "Mägi";
        String idCode = "40312132226";
        int age = AGE24;
        Worker newWorker = new Worker(firstName, lastName, idCode, age);
        newWorker.setFirstName("Magnus");
        assertEquals("Magnus", newWorker.getFirstName());
        assertEquals(("Magnus" + " " + lastName + ", " + age + " (" + idCode + ")"), newWorker.getWorkerSummary());
    }

    @Test
    void setLastName() {
        String firstName = "Mark";
        String lastName = "Mägi";
        String idCode = "40312132226";
        int age = AGE24;
        Worker newWorker = new Worker(firstName, lastName, idCode, age);
        newWorker.setLastName("Raud");
        assertEquals("Raud", newWorker.getLastName());
        assertEquals((firstName + " " + "Raud" + ", " + age + " (" + idCode + ")"), newWorker.getWorkerSummary());
    }

    @Test
    void setIdCode() {
        String firstName = "Mark";
        String lastName = "Mägi";
        String idCode = "40312132226";
        int age = AGE24;
        Worker newWorker = new Worker(firstName, lastName, idCode, age);
        newWorker.setIdCode("3430000000");
        assertEquals("3430000000", newWorker.getIdCode());
        assertEquals((firstName + " " + lastName + ", " + age + " (" + "3430000000" + ")"),
                newWorker.getWorkerSummary());
    }

    @Test
    void setAge() {
        String firstName = "Mark";
        String lastName = "Mägi";
        String idCode = "40312132226";
        Worker newWorker = new Worker(firstName, lastName, idCode, AGE24);
        newWorker.setAge(AGE33);
        assertEquals(AGE33, newWorker.getAge());
        assertEquals((firstName + " " + lastName + ", " + AGE33 + " (" + idCode + ")"), newWorker.getWorkerSummary());
    }
}
