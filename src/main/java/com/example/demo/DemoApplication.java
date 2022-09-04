package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class DemoApplication {
    
    @Autowired
	private ConsentRepositry consentRepositry;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    
    @GetMapping(value= "/{id}", produces = "application/json")
	public List<Consent> getConsent(@PathVariable("id") String id) {
		
		System.out.println("call database!!!! ");
		//Optional<Consent> entity = consentRepositry.findById("1");
		List<Consent> consent = consentRepositry.findAll();
		
		
		return consent;
		
	}

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
