package com.example.demo.api;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;

@Validated
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /user : creates a user
     * Creates a new user to the system
     *
     * @param user Inventory item to add (required)
     * @return user created (status code 201)
     *         or an existing user already exists (status code 409)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/user",
            consumes = { "application/json" }
    )
    default ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /user/{userId} : deletes a user
     * Deletes a user from the system
     *
     * @param userId  (required)
     * @return user deleted (status code 200)
     *         or resource not found (status code 404)
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/user/{userId}"
    )
    default ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /user/{userId} : get user by id
     *
     * @param userId  (required)
     * @return get user object (status code 200)
     *         or resource not found (status code 404)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{userId}",
            produces = { "application/json" }
    )
    default ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"address\" : \"Via Rossi, Firenze\", \"surname\" : \"Rossi\", \"name\" : \"Mario\", \"userId\" : 1, \"email\" : \"infomr@afaef.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users : searches users by name and by surname
     *
     * @param name  (optional)
     * @param surname  (optional)
     * @param pageable  (optional)
     * @return search results matching criteria (status code 200)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users",
            produces = { "application/json" }
    )
    default ResponseEntity<Page<User>> searchUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname, Pageable pageable/*@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "50") Integer size, @RequestParam(value = "sort", required = false) String sort*/) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"content\" : [ { \"address\" : \"Via Rossi, Firenze\", \"surname\" : \"Rossi\", \"name\" : \"Mario\", \"userId\" : 1, \"email\" : \"infomr@afaef.com\" }, { \"address\" : \"Via Rossi, Firenze\", \"surname\" : \"Rossi\", \"name\" : \"Mario\", \"userId\" : 1, \"email\" : \"infomr@afaef.com\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /user : updates a user
     * Updates a new user to the system
     *
     * @param user Inventory item to update (required)
     * @return user updated (status code 201)
     *         or resource not found (status code 404)
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/user",
            consumes = { "application/json" }
    )
    default ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}