package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-25T17:44:26.218+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.simpleUser.base-path:/demo/1.0.0}")
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
    public ResponseEntity<User> createUser(@RequestBody(required = false) User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(@RequestBody(required = false) User user) {
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<User>(userService.findUser(userId), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

}
