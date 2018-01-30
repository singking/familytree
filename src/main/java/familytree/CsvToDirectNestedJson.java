package familytree;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.google.common.io.Files;

@Component
public class CsvToDirectNestedJson {

	Map<Long, Person> peopleMap = Collections.emptyMap();

	AtomicLong personSequence = new AtomicLong(0);

	public CsvToDirectNestedJson() {
	}

	public Person insert(Person p) {
		if (p.getId() > 0) {
			throw new IllegalStateException("person ID already exists: " + p);
		}

		long id = personSequence.getAndIncrement();
		p.setId(id);
		peopleMap.put(id, p);
		return p;
	}

	public void addchild(Person child) {
		if (child.getMotherId() == null && child.getFatherId() == null) {
			throw new IllegalStateException("child should have at least one parent: " + child);
		}

		Person fatherFound = null;
		if (child.getFatherId() != null) {
			fatherFound = peopleMap.get(child.getFatherId());
			if (fatherFound != null) {
				fatherFound.getChildren().add(child);

			}
		}

		Person motherFound = null;
		if (child.getMotherId() != null) {
			motherFound = peopleMap.get(child.getMotherId());
			if (motherFound != null) {
				motherFound.getChildren().add(child);

			}
		}

		if (child.getId() > 0) {
			Person childFound = peopleMap.get(child.getId());
			// throw new IllegalStateException("Child ID already exists: " + child);
		} else {
			long id = personSequence.incrementAndGet();
			child.setId(id);

			peopleMap.put(id, child);

		}
	}

	public void update(Person p) {
		Person personFound = peopleMap.get(p.getId());
		if (personFound == null) {
			throw new IllegalStateException("person ID not found: " + p);
		}

		personFound.setChildren(p.getChildren());
		personFound.setFatherId(p.getFatherId());
		personFound.setMotherId(p.getMotherId());
		personFound.setName(p.getName());
	}

	public Collection<Person> loadFamilyTree() throws IOException {
		peopleMap = parseFile();

		Collection<Person> roots = createTreeModel(peopleMap);

		if (roots.size() != 1) {

			String rootString = "";
			for (Person p : roots) {
				rootString += p.getName() + " ";
			}

			throw new RuntimeException("expecting one root=>" + rootString);
		}

		personSequence.set(calculateMaxId(peopleMap));
		return roots;
	}

	public long calculateMaxId(Map<Long, Person> people) {
		long maxId = -1;
		for (Person p : people.values()) {
			maxId = maxId < p.getId() ? maxId = p.getId() : maxId;
		}
		return maxId;
	}

	public Collection<Person> createTreeModel(Map<Long, Person> people) {
		Collection<Person> roots = new ArrayList<>();

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

		return roots;
	}

	public Map<Long, Person> parseFile() throws IOException, NumberFormatException {
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
		return people;
	}
}
