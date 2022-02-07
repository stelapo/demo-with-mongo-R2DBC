package com.example.demo.api;

import com.example.demo.model.SearchCriteria;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
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
     * or an existing user already exists (status code 409)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/user",
            consumes = {"application/json"}
    )
    default ResponseEntity<Mono<User>> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * DELETE /user/{userId} : deletes a user
     * Deletes a user from the system
     *
     * @param userId (required)
     * @return user deleted (status code 204)
     * or resource not found (status code 404)
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
     * @param userId (required)
     * @return get user object (status code 200)
     * or resource not found (status code 404)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{userId}",
            produces = {"application/json"}
    )
    default ResponseEntity<Mono<User>> getUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * GET /users : searches users by name and by surname
     *
     * @param name     (optional)
     * @param surname  (optional)
     * @param pageable (optional)
     * @return search results matching criteria (status code 200)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users",
            produces = {"application/json"}
    )
    default ResponseEntity<Page<User>> searchUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname, Pageable pageable) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /user : updates a user
     * Updates a new user to the system
     *
     * @param userId (required)
     * @param user   Inventory item to update (required)
     * @return user updated (status code 201)
     * or resource not found (status code 404)
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/user/{userId}",
            consumes = {"application/json"}
    )
    default ResponseEntity<Mono<User>> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users : searches users by name and by surname
     *
     * @param searchString (optional)
     * @param pageable     (optional)
     * @return search results matching criteria (status code 200)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/search",
            produces = {"application/json"}
    )
    default ResponseEntity<Page<User>> searchUsers(@Pattern(regexp = SearchCriteria.searchStringPatternForController) @RequestParam(value = "searchString", required = false) String searchString, Pageable pageable) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}