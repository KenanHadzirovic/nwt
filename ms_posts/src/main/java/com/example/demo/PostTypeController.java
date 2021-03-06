package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping("/api/posttype")
public class PostTypeController {
	@Autowired
    IPostRepository postRepository;

    @Autowired
    IPostTypeRepository posttypeRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/{name}")
    public ResponseEntity create(@PathVariable String name, @RequestBody PostType postType) throws ParseException {
        postType.setId(null);
        postType.setPostTypeName(name);
        
        PostType result = posttypeRepository.save(postType);
      
        return new ResponseEntity<>(result.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PostType>> geTAll()
    {
        List<PostType> result = posttypeRepository.findAll();
        return new ResponseEntity<List<PostType>>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws ParseException {
    
        List<PostType> result = posttypeRepository.findAll();
     
        for (PostType p : result) {
        	if(p.getId()==id)
        	{
                posttypeRepository.deleteById(id);
        	}
        	else 
        	{
                return new ResponseEntity<>("Post type with that id does not exist.", HttpStatus.NOT_FOUND);
        	}
        }
       
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
   /* @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public ResponseEntity delete(@PathVariable String name, @RequestBody PostType postType) throws ParseException {
        postType.setId(null);
        postType.setPostTypeName(name);
        
        posttypeRepository.delete(postType);
       
        return new ResponseEntity<>(name, HttpStatus.OK);
    }*/
    
    

}
