package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {
    Flux<User> findByNameOrSurname (String name, String surname, Pageable pageable);

    Flux<User> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase (String name, String surname, Pageable pageable);


}
