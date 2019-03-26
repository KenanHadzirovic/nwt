package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")

public class PostController {
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	  public ResponseEntity<String> getAll() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }
	
	
}
