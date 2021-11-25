package com.example.demo.api;

import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-25T17:44:26.218+01:00[Europe/Berlin]")
@Validated
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /user : creates a user
     * Creates a new user to the system
     *
     * @param user Inventory item to add (optional)
     * @return user created (status code 201)
     *         or an existing user already exists (status code 409)
     */
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/user",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> createUser(@RequestBody(required = false) User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /user/{userId} : deletes a user
     * Deletes a user from the system
     *
     * @param userId  (required)
     * @return user deleted (status code 201)
     */
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/user/{userId}"
    )
    default ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /user : gets users
     *
     * @param name  (optional)
     * @param surname  (optional)
     * @return search results matching criteria (status code 200)
     *         or bad input parameter (status code 400)
     */
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/user",
        produces = { "application/json" }
    )
    default ResponseEntity<List<User>> getUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"birthday\" : \"1982-05-01\", \"address\" : \"Via Rossi, Firenze\", \"surname\" : \"Pancani\", \"name\" : \"Lapo\", \"userId\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\" }";
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
     * @param user Inventory item to update (optional)
     * @return user updated (status code 201)
     */
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/user",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> updateUser(@RequestBody(required = false) User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
