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

@RestController
@RequestMapping("/api/post")

public class PostController {
	
	@Autowired
    IPostRepository postRepository;

    @Autowired
    IWorkspaceRepository workspaceRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/workspace/{workspaceId}")
    public ResponseEntity create(@PathVariable Long workspaceId, @RequestBody Post post) throws ParseException {
        post.setId(post.getId());

        post.setPostType(workspaceId);
        post.setWorkspace(workspaceId);

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

    
       Optional<Workspace> worksp = workspaceRepository.findById(workspaceId);
       // post.setWorkspace(workspaceId);
        Post result = postRepository.save(post);
       // result.setWorkspace(worksp.);

        if (result == null) {
            return new ResponseEntity<>("Unable to save the post.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> geTAll()
    {
        List<Post> result = postRepository.findAll();
        return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
    }

   /* @RequestMapping(method = RequestMethod.PUT, value = "/{workspaceId}")
    public ResponseEntity update(@PathVariable Long workspaceId, @RequestBody Workspace workspace) throws ParseException {
        Optional<Workspace> optDbWorkspace = workspaceRepository.findById(workspaceId);

        if (!optDbWorkspace.isPresent()) {
            return new ResponseEntity<>("Workspace not found by provided id", HttpStatus.NOT_FOUND);
        }

        Workspace db = optDbWorkspace.get();

        db.setTitle(workspace.getTitle());
        db.setDescription(workspace.getDescription());
        db.setDeleted(workspace.isDeleted());

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        //db.setModifiedDate(dateFormatLocal.parse( dateFormatGmt.format(new Date())));

        workspaceRepository.save(db);

        return new ResponseEntity<>(workspaceId, HttpStatus.OK);
    }
	*/
	
}
