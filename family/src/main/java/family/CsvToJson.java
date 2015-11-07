package family;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.base.Splitter;
import com.google.common.io.Files;

public class CsvToJson {

	public static void main(String[] args) throws IOException {
		Map<String, Person> people = new HashMap<>();
		List<Person> roots = new ArrayList<>();

		List<String> readLines = Files.readLines(new File("familytree.csv"),
				Charset.defaultCharset());
		for (String string : readLines) {
			List<String> columns = Splitter.on(',').trimResults()
					.splitToList(string);
			String id = columns.get(0);
			if (id.equals("ID")) {
				// ignore header
				continue;
			}
			Person person = people.get(columns.get(0));
			if (person == null) {
				String firstname = columns.get(3);
				String middlename = columns.get(4);
				String secondname = columns.get(5);

				if ((firstname == null || firstname.equals(""))
						&& (secondname == null || secondname.equals(""))) {
					continue;
				}

				person = new Person(id, firstname + " " + middlename + " "
						+ secondname);
				people.put(id, person);

				String motherId = columns.get(1);
				String fatherId = columns.get(2);

				Person mother = people.get(motherId);
				Person father = people.get(fatherId);

				if (mother != null) {
					mother.getChildren().add(person);
				}
				if (father != null) {
					father.getChildren().add(person);
				}
				if (father == null && mother == null) {
					roots.add(person);
				}
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(SerializationConfig.INDENT_OUTPUT);

		// Object to JSON in file
		mapper.writeValue(new File("family.json"), people);

		// Object to JSON in String
		String jsonInString = mapper.defaultPrettyPrintingWriter()
				.writeValueAsString(roots);

		System.out.println(jsonInString);
	}
}
