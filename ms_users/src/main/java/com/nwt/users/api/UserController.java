package com.nwt.users.api;

import com.nwt.users.entities.User;
import com.nwt.users.repository.interfaces.UserRepository;
import com.nwt.users.repository.interfaces.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<User>> getAll() {
        List<User> result = userRepository.findAll();

        return new ResponseEntity<List<User>>(result, HttpStatus.OK);
    }
}
