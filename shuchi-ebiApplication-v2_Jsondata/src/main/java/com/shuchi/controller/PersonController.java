package com.shuchi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shuchi.dao.PersonDAO;
import com.shuchi.model.Person;
import com.shuchi.model.Persons;


@RestController
@RequestMapping(path = "/persons")
public class PersonController 
{
    @Autowired
    private PersonDAO personDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Persons getPersons() 
    {
        return personDao.getAllPersons();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) 
                 throws Exception 
    {       
        //Generate resource id
        Integer id = personDao.getAllPersons().getPersonList().size() + 1;
        person.setId(id);
        
        //add resource
        personDao.addPerson(person);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(person.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
	public ResponseEntity<Long> updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody Person personDetails) throws Exception {
		
    	Person person = personDao.findById(personId);
		if (person==null)
		{	return new ResponseEntity<>(personId, HttpStatus.BAD_REQUEST);}
		else
		{
		person.setFavourite_colour(personDetails.getFavourite_colour());
		person.setAge(personDetails.getAge());
		person.setLastName(personDetails.getLastName());
		person.setFirstName(personDetails.getFirstName());
		 return new ResponseEntity<>(personId, HttpStatus.ACCEPTED);}
		//return ResponseEntity.ok(person);}
		
    
	}
	@DeleteMapping("/{id}")
	
		public ResponseEntity<Long> deletePerson(@PathVariable Long id) throws Exception {

	        Boolean isRemoved = personDao.delete(id);

	        if (!isRemoved) {
	        	return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
	        }
	      

	        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);	       
	    }

}
	