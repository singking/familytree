package familytree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private static Map<Long, Person> items = new HashMap<>();
	private int nextId = 1;

	// Populate collection with some simple Items, to get the ball rolling.
	{
        // insert(new Person(1L, "mum A", null, null));
        // insert(new Person(2L, "dad A", null, null));
        // insert(new Person(3L, "son A1", 1L, 2L));
        // insert(new Person(4L, "daughter A1", 1L, 2L));
	}

	@Override
    public Person getItem(int id) {
		return items.get(id);
	}

	@Override
    public Collection<Person> getItems() {
		return items.values();
	}

	@Override
    public void insert(Person item) {
		item.setId(nextId++);
		items.put(item.getId(), item);
	}

	@Override
    public void update(Person item) {
        long id = item.getId();
		if (items.containsKey(id)) {
			items.put(id, item);
		}
	}

	@Override
	public void delete(int id) {
        Person item = items.get(id);
		if (item != null) {
			items.remove(id);
		}
	}
}
