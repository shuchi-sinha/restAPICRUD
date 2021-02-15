package com.shuchi;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.shuchi.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

public class ShuchiEbiApplicationV2JsondataApplicationTests 
{
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddPersonSuccess() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/Persons/";
        URI uri = new URI(baseUrl);
        Person Person = new Person(null, "Adam", "Gilly", "test@email.com");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        HttpEntity<Person> request = new HttpEntity<>(Person, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
    }
    
    @Test
    public void testAddPersonMissingHeader() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/Persons/";
        URI uri = new URI(baseUrl);
        Person Person = new Person(null, "Adam", "Gilly", "test@email.com");
        
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Person> request = new HttpEntity<>(Person, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        //Verify bad request and missing header
        Assert.assertEquals(400, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("Missing request header"));
    }
}