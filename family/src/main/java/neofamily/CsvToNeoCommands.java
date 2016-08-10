package neofamily;

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

public class CsvToNeoCommands {

	public static void main(String[] args) throws IOException {
		Map<String, NeoPerson> people = new HashMap<>();
		List<NeoPerson> roots = new ArrayList<>();

		List<String> readLines = Files.readLines(new File("familytree.csv"), Charset.defaultCharset());
		for (String string : readLines) {
			List<String> columns = Splitter.on(',').trimResults().splitToList(string);
			String id = columns.get(0);
			if (id.equals("ID")) {
				// ignore header
				continue;
			}

			String motherId = columns.get(1);
			String fatherId = columns.get(2);
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
			NeoPerson person = new NeoPerson(id, name, motherId, fatherId);
			people.put(id, person);

		}
		
		ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(SerializationConfig.INDENT_OUTPUT);

		// Object to JSON in file
		// mapper.writeValue(new File("family.json"), people);
		mapper.writeValue(new File("neofamily.json"), roots);

		// Object to JSON in String
		String jsonInString = mapper.defaultPrettyPrintingWriter().writeValueAsString(roots);

		System.out.println(jsonInString);

		// node
		for (NeoPerson p : people.values()) {
			System.out.println(String.format("CREATE (%s);",p.toString()));
		}

		// relationship
		for (NeoPerson p : people.values()) {
			NeoPerson mother = people.get(p.getMotherId());
			NeoPerson father = people.get(p.getFatherId());

			if (mother != null) {
			}
			if (father != null) {
			}
			if (father == null && mother == null) {
				roots.add(p);
			}
		}

		if (roots.size() != 1) {

			String rootString = "";
			for (NeoPerson p : roots) {
				rootString += p.getName() + " ";
			}

			throw new RuntimeException("expecting one root=>" + rootString);
		}

	}
}
