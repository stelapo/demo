package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("${simpleUser.basepath:/demo/1.0.0}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;

    @Autowired
    UserService userService;


    @Autowired
    public UsersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User user) {
        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                return new ResponseEntity<User>(userService.updateUser(userId, user), HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                return new ResponseEntity<User>(userService.findUser(userId), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Page<User>> searchUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname, Pageable pageable) {
        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                return new ResponseEntity<>(userService.findByNameAndSurname(name, surname, pageable), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
