package familytree;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.google.common.io.Files;

@Component
public class CsvToDirectNestedJson {
    private static Collection<Person> roots = new ArrayList<>();

    public CsvToDirectNestedJson() {
    }

    public static Collection<Person> getRoots() {
        return roots;
    }

    public Collection<Person> loadFamilyTree() throws IOException {

        roots = new ArrayList<>();

        Map<Long, Person> people = new HashMap<>();

        String csvFilename = System.getProperty("familytree.csv.file");
        
        List<String> readLines = Files.readLines(new File(csvFilename), Charset.defaultCharset());
        for (String string : readLines) {
            List<String> columns = Splitter.on(',').trimResults().splitToList(string);
            String idString = columns.get(0);
            if (idString.equals("ID")) {
                // ignore header
                continue;
            }

            String motherStringId = columns.get(1);
            String fatherStringId = columns.get(2);
            String firstname = columns.get(3);
            String middlename = columns.get(4);
            String secondname = columns.get(5);

            if ((firstname == null || firstname.equals("")) && (secondname == null || secondname.equals(""))) {
                continue;
            }

            String name = firstname;
            if (middlename != null) {
                middlename = middlename.trim();
                if (!middlename.equals("")) {
                    name = name + " " + middlename;
                }
            }
            if (secondname != null) {
                secondname = secondname.trim();
                if (!secondname.equals("")) {
                    name = name + " " + secondname;
                }
            }

            long id = Long.parseLong(idString);
            Long motherId = "".equals(motherStringId) ? null : Long.parseLong(motherStringId);
            Long fatherId = "".equals(fatherStringId) ? null : Long.parseLong(fatherStringId);

            Person person = new Person(id, name, motherId, fatherId);
            people.put(id, person);

        }
        for (Person p : people.values()) {
            Person mother = people.get(p.getMotherId());
            Person father = people.get(p.getFatherId());

            if (mother != null) {
                mother.getChildren().add(p);
            }
            if (father != null) {
                father.getChildren().add(p);
            }
            if (father == null && mother == null) {
                roots.add(p);
            }
        }

        if (roots.size() != 1) {

            String rootString = "";
            for (Person p : roots) {
                rootString += p.getName() + " ";
            }

            throw new RuntimeException("expecting one root=>" + rootString);
        }
        // ObjectMapper mapper = new ObjectMapper();
        // // mapper.enable(SerializationConfig.INDENT_OUTPUT);
        //
        // // Object to JSON in file
        // // mapper.writeValue(new File("family.json"), people);
        // mapper.writeValue(new File("family.json"), roots);
        //
        // // Object to JSON in String
        // String jsonInString = mapper.defaultPrettyPrintingWriter()
        // .writeValueAsString(roots);
        //
        // return jsonInString;

        return roots;
    }
}
