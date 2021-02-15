package com.shuchi.dao;

import org.springframework.stereotype.Repository;

import com.shuchi.model.Person;
import com.shuchi.model.Persons;

@Repository
public class PersonDAO {
	private static Persons list = new Persons();

	static {
		list.getPersonList().add(new Person("John", "Keynes", "29", "red"));
		list.getPersonList().add(new Person("Sarah", "Robinson", "54", "blue"));
	}

	public Persons getAllPersons() {
		return list;
	}

	public void addPerson(Person person) {
		list.getPersonList().add(person);
	}

	public Person findById(Long id) {
		for (Person p : list.getPersonList()) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}

	public boolean delete(Long id) {
		for (Person p : list.getPersonList()) {

			if (p.getId() == id)
				list.getPersonList().remove(p);
			return true;
		}
		return false;
	}

}