package com.king.familytree;

import java.util.Collection;

import family.Person;

public interface PersonService {

    Person getItem(int id);

    Collection<Person> getItems();

    void insert(Person item);

    void update(Person item);
	void delete(int id);
}
