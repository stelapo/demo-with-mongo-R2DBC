package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DatabaseSequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;

    private DatabaseSequenceService databaseSequenceService;

    public DataInitializer(UserRepository userRepository, DatabaseSequenceService databaseSequenceService) {
        this.userRepository = userRepository;
        this.databaseSequenceService = databaseSequenceService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("start data initialization  ...");
        /*this.userRepository
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Pippo", "Pluto")
                                .flatMap(
                                        name -> this.userRepository.save(User.builder().name(name).surname("surnma of " + name).email(name + "@gmail.com").userId(databaseSequenceService.generateSequence(User.SEQUENCE_NAME)).build())
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done initialization...")
                );*/
        log.info("initialization chained  ...");
    }
}
