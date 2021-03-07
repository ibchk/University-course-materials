package ee.taltech.iti0200.personstatistics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.HashSet;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonStatisticsTest {
    private static final double HEIGHT170 = 1.7;
    private static final double HEIGHT190 = 1.9;
    private static final int AGE60 = 60;
    private static final int AGE44 = 44;
    private static final int AGE32 = 32;
    private static final int AGE55 = 55;
    private static final double HEIGHT170CM = 170.0;
    private static final double HEIGHT180CM = 180.0;

    @org.junit.jupiter.api.Test
    void countPersons3persons() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(3, statistics.countPersons());
    }

    @org.junit.jupiter.api.Test
    void findAverageHeight() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(OptionalDouble.of(HEIGHT170), statistics.findAverageHeight());
    }

    @org.junit.jupiter.api.Test
    void findYoungestPersonAndCheckIfCorrectPerson() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        Optional<Person> person = statistics.findYoungestPerson();
        person.get().setAge(AGE60);
        person.get().setFirstName("Sandy");
        person.get().setLastName("Mets");
        person.get().setGender(Gender.MALE);
        person.get().setNationality("estonian");
        person.get().setHeightInMeters(HEIGHT190);
        person.get().setOccupation("medic");
        assertEquals("medic", person.get().getOccupation());
        assertEquals("estonian", person.get().getNationality());
        assertEquals("Sandy", person.get().getFirstName());
        assertEquals("Mets", person.get().getLastName());
        assertEquals(AGE60, person.get().getAge());
        assertEquals(HEIGHT190, person.get().getHeightInMeters());
        assertEquals(Gender.MALE, person.get().getGender());
    }

    @org.junit.jupiter.api.Test
    void findYoungestPerson() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        Optional<Person> person = statistics.findYoungestPerson();
        assertEquals("nurse", person.get().getOccupation());
    }

    @org.junit.jupiter.api.Test
    void findOldestPerson() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals("mary", statistics.findOldestPerson().get().getFirstName());
    }

    @org.junit.jupiter.api.Test
    void findOldestPersonEmpty() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(Optional.empty(), statistics.findOldestPerson());
    }

    @org.junit.jupiter.api.Test
    void findLongestLastName() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(Optional.of("metsi"), statistics.findLongestLastName());
    }

    @org.junit.jupiter.api.Test
    void getNationalityData() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.6,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        List<String> nationalities = new LinkedList<>(Arrays.asList("estonia", "estonia", "uk"));
        assertEquals(nationalities, statistics.getNationalityData());
    }

    @org.junit.jupiter.api.Test
    void getHeightInCm() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.8,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        List<Double> heights = new LinkedList<>(Arrays.asList(HEIGHT180CM, HEIGHT170CM, HEIGHT180CM));
        assertEquals(heights, statistics.getHeightInCm());
    }

    @org.junit.jupiter.api.Test
    void findSamples() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.8,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(2, statistics.findSamples(2).size());
    }

    @org.junit.jupiter.api.Test
    void findSamplesZero() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.8,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(0, statistics.findSamples(0).size());
    }

    @org.junit.jupiter.api.Test
    void findSamplePerson() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.8,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals("mary",
                statistics.findSamplePerson("uk", Gender.FEMALE, AGE44).get().getFirstName());
    }

    @org.junit.jupiter.api.Test
    void getDistinctFirstNamesNoDuplicates() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,metsi,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.8,manager,uk\n"
                + "martin,smith,44,FEMALE,1.8,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        Set<String> set = new HashSet<>();
        set.add("martin");
        set.add("mari");
        set.add("mary");
        assertEquals(set, statistics.getDistinctFirstNames());
    }

    @org.junit.jupiter.api.Test
    void getReverseOrderedByHeight() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.9,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals("mary", statistics.getReverseOrderedByHeight().get(0).getFirstName());
    }

    @org.junit.jupiter.api.Test
    void findBetweenAge() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.9,manager,uk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals("martin", statistics.findBetweenAge(AGE32, AGE55).get(0).getFirstName());
    }

    @org.junit.jupiter.api.Test
    void findSameLetterNameAndNationality() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.9,manager,muk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals("mary", statistics.findSameLetterNameAndNationality().get(0).getFirstName());
        assertEquals(1, statistics.findSameLetterNameAndNationality().size());
    }

    @org.junit.jupiter.api.Test
    void mapOccupationToPersons() throws IOException {
        FileWriter writer = new FileWriter("persons.csv");
        writer.write("martin,mets,32,MALE,1.8,driver,estonia\n"
                + "mari,mets,31,FEMALE,1.7,nurse,estonia\n"
                + "mary,smith,44,FEMALE,1.9,nurse,muk");
        writer.close();
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        assertEquals(2, statistics.mapOccupationToPersons().size());
    }

    @org.junit.jupiter.api.Test
    void catchException() {
        boolean isItCatched = false;
        try {
            CsvPersonMapper mapper = new CsvPersonMapper();
            List<Person> persons = mapper.mapToPersons("pereesons.csv");
        } catch (CsvToPersonMappingException e) {
            isItCatched = true;
        }
        assertTrue(isItCatched);
    }
}
