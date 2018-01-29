package family;

import java.util.ArrayList;
import java.util.List;

public class Person {
	String id;
	String name;
	String motherId;
	String fatherId;
	
	List<Person> children = new ArrayList<>();

	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Person(String id, String name, String motherId, String fatherId) {
		super();
		this.id = id;
		this.name = name;
		this.motherId = motherId;
		this.fatherId = fatherId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", children=" + children + "]";
	}

}
