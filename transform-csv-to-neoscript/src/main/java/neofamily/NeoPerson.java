package neofamily;

public class NeoPerson {
	String id;
	String name;
	String motherId;
	String fatherId;

	public NeoPerson(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public NeoPerson(String id, String name, String motherId, String fatherId) {
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

	public NeoPerson(String name) {
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

	@Override
	public String toString() {
		return String.format("p%s {id='%s' name='%s' fatherId='%s' motherId='%s'} ", id, id, name, fatherId, motherId);
	}

}
