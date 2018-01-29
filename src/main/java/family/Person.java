package family;

import java.util.ArrayList;
import java.util.List;

public class Person {
    long id;
	String name;
    Long motherId;
    Long fatherId;

	List<Person> children = new ArrayList<>();

    public Person(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

    public Person(long id, String name, Long motherId, Long fatherId) {
		super();
		this.id = id;
		this.name = name;
		this.motherId = motherId;
		this.fatherId = fatherId;
	}

    public long getId() {
		return id;
	}

    public void setId(long id) {
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

    public Long getMotherId() {
		return motherId;
	}

    public void setMotherId(Long motherId) {
		this.motherId = motherId;
	}

    public Long getFatherId() {
		return fatherId;
	}

    public void setFatherId(Long fatherId) {
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
