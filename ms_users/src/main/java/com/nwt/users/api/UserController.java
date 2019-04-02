package com.nwt.users.api;

import com.nwt.users.entities.User;
import com.nwt.users.entities.Workspace;
import com.nwt.users.repository.interfaces.UserRepository;
import com.nwt.users.repository.interfaces.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public ResponseEntity getById(@PathVariable Long userId) {
        Optional<User> result = userRepository.findById(userId);

        if (!result.isPresent()) {
            return new ResponseEntity<>("User not found by provided id", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(result.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity create(@RequestBody User user) throws ParseException {
        user.setId(null);

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        user.setCreatedDate(dateFormatLocal.parse(dateFormatGmt.format(new Date())));

        User result = userRepository.save(user);

        if (result == null) {
            return new ResponseEntity<>("Unable to save the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public ResponseEntity update(@PathVariable Long userId, @RequestBody User user) throws ParseException {
        Optional<User> optDbUser = userRepository.findById(userId);

        if (!optDbUser.isPresent()) {
            return new ResponseEntity<>("User not found by provided id", HttpStatus.NOT_FOUND);
        }

        User db = optDbUser.get();

        db.setUsername(user.getUsername());
        db.setPassword(user.getPassword());
        db.setFirstName(user.getFirstName());
        db.setMiddleName(user.getMiddleName());
        db.setLastName(user.getLastName());
        db.setActive(user.isActive());

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        db.setModifiedDate(dateFormatLocal.parse( dateFormatGmt.format(new Date())));

        userRepository.save(db);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/workspaces")
    public ResponseEntity getByUserId(@PathVariable Long userId) {
        List<Workspace> result = workspaceRepository.getByUserOwnerId(userId);

        return new ResponseEntity<List<Workspace>>(result, HttpStatus.OK);
    }
}
