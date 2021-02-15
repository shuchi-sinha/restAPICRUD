package com.shuchi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Persons {
@JsonProperty("person")
	 private List<Person> personList;
	    
	    public List<Person> getPersonList() {
	        if(personList == null) {
	        	personList = new ArrayList<>();
	        }
	        return personList;
	    }
	 
	    public void setEmployeeList(List<Person> personList) {
	        this.personList = personList;
	    }
}
