package ee.taltech.iti0200.personstatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CsvPersonMapper {

    public static final int GROUPINDEX6 = 6;
    public static final int GROUPINDEX7 = 7;

    public Matcher findMatcher(String line, Pattern regex) {
        Matcher m = regex.matcher(line);
        m.matches();
        m.groupCount();
        return m;
    }

    public List<Person> mapToPersons(String path) {
        List<Person> newList = new ArrayList<>();
        try {
            Stream<String> stream = Files.lines(Paths.get(path));
            Pattern regex = Pattern.compile("(.*),(.*),(.*),(.*),(.*),(.*),(.*)");
            stream.forEach(line -> newList.add(new PersonBuilder()
                    .withFirstName(findMatcher(line, regex).group(1))
                    .withLastName(findMatcher(line, regex).group(2))
                    .withAge(Integer.parseInt(findMatcher(line, regex).group(3)))
                    .withGender(Gender.valueOf(findMatcher(line, regex).group(4)))
                    .withHeightInMeters(Double.parseDouble(findMatcher(line, regex).group(5)))
                    .withOccupation(findMatcher(line, regex).group(GROUPINDEX6))
                    .withNationality(findMatcher(line, regex).group(GROUPINDEX7))
                    .build()));
        } catch (IOException e) {
            throw new CsvToPersonMappingException();
        }
        return newList;
    }
}
