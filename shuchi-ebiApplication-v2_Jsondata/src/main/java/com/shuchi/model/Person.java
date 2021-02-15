package com.shuchi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"first_name", "last_name", "age","favourite_colour"} )
public class Person {
	
	//using id to avoid issues with duplicate data, 
//	and ensuring id is not displaying in json response as provided in the test problem
	@JsonIgnore
	private long id;
	
	private String first_name;
	private String last_name;
	// age is string as per input so e.g 29 /twenty nine / 29years 2 months, 29.2 etc will be accepted
	private String age;
	private String favourite_colour;
	public Person() {
		
	}
	public Person(String first_name, String last_name, String age, String favourite_colour) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
	}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
   
 //to avoid internal conversion to firstName  
    @JsonProperty("first_name")
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }
      @JsonProperty("last_name")
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String lastName) {
        this.last_name = lastName;
    }
  
    public String getAge() {
  		return age;
  	}
  	public void setAge(String age) {
  		this.age = age;
  	}
  	public String getFavourite_colour() {
  		return favourite_colour;
  	}
  	public void setFavourite_colour(String favourite_colour) {
  		this.favourite_colour = favourite_colour;
  	}
}