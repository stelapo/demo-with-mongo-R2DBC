package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Page<User> findByNameOrSurname (String name, String surname, Pageable pageable);

    Page<User> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase (String name, String surname, Pageable pageable);


}
