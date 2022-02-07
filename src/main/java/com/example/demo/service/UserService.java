package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> createUser(User user);

    Mono<User> updateUser(Long userId, User user);

    Mono<User> findUser(Long userId);

    void deleteUser(Long userId);

    Flux<User> findAll(Pageable pageable);

    Page<User> findByNameAndSurname(String name, String surname, Pageable pageable);

    Page<User> findBySearchString(String searchString, Pageable pageable);
}
