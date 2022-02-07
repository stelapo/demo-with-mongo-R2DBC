package com.example.demo.api;

import com.example.demo.model.SearchCriteria;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
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
    public ResponseEntity<Mono<User>> createUser(@Valid @RequestBody User user) {
        if (ApiUtil.applicationJsonHeaderExists(request)) {
            return new ResponseEntity<Mono<User>>(userService.createUser(user), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Mono<User>> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User user) {
        if (ApiUtil.applicationJsonHeaderExists(request)) {
            return new ResponseEntity<Mono<User>>(userService.updateUser(userId, user), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Mono<User>> getUser(@PathVariable("userId") Long userId) {
        if (ApiUtil.applicationJsonHeaderExists(request)) {
            return new ResponseEntity<Mono<User>>(userService.findUser(userId), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Flux<User>> searchUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname, Pageable pageable) {
        if (ApiUtil.applicationJsonHeaderExists(request)) {
            return new ResponseEntity<>(userService.findByNameAndSurname(name, surname, pageable), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Flux<User>> searchUsers(@Pattern(regexp = SearchCriteria.searchStringPatternForController) @RequestParam(value = "searchString", required = false) String searchString, Pageable pageable) {
        if (ApiUtil.applicationJsonHeaderExists(request)) {
            return new ResponseEntity<>(userService.findBySearchString(searchString, pageable), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
