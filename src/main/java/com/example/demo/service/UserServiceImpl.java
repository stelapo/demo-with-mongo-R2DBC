package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SearchCriteria;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.specification.UserQueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<User> createUser(User user) {
        user.setUserId(-1l);
        return userRepository.save(user);
    }

    @Override
    public Mono<User> updateUser(Long userId, User user) {
        Mono<User> userFoundAndUpdated = findUser(userId)
                //.switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found with id " + userId))) //Fallback to an alternative Mono if this mono is completed without data
                .flatMap(u -> { //uso flatMap perchè ricevo l'entity User e restituisco il Mono<User>; con map dovrei restituire sempre l'entity
                    user.setUserId(u.getUserId());
                    //return user;
                    return userRepository.save(user); //errore, perchè save restituisce l'entity
                })
                //.flatMap(userRepository::save)
                ;
        return userFoundAndUpdated;

    }

    @Override
    public Mono<User> findUser(Long userId) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found with id " + userId))) //Fallback to an alternative Mono if this mono is completed without data
                ;
    }

    @Override
    public void deleteUser(Long userId) {
        //findUser(userId).flatMap(user -> userRepository.delete(user)).block(Duration.ofSeconds(3));
        findUser(userId).subscribe(successValue -> {
                    log.info(successValue.getUserId() + "");
                    userRepository.delete(successValue)
                            .subscribe(null,
                                    null,
                                    () -> log.info("Deleted user!"));
                },
                errorConsumer -> log.error("", errorConsumer),
                () -> log.info("Found user!"));
    }

    @Override
    public Flux<User> findAll(Pageable pageable) {
        return userRepository.findAll();
    }


    @Override
    public Flux<User> findByNameAndSurname(String name, String surname, Pageable pageable) {
        return userRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name == null ? "" : name, surname == null ? "" : surname, pageable);

    }

    @Override
    public Flux<User> findBySearchString(String searchString, Pageable pageable) {
        UserQueryBuilder builder = new UserQueryBuilder();
        Pattern pattern = Pattern.compile(SearchCriteria.searchStringPattern);
        Matcher matcher = pattern.matcher(searchString);
        while (matcher.find()) {
            builder.addSearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Query query = builder.build().with(pageable);
        Flux<User> userList = reactiveMongoTemplate.find(query, User.class);
        return userList;
        /*return PageableExecutionUtils.getPage(
                userList,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), User.class));*/

    }
}
