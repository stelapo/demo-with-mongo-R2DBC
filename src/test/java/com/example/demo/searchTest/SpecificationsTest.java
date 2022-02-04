package com.example.demo.searchTest;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/*@DataJpaTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)*/
public class SpecificationsTest {

    /*@Autowired
    UserRepository repository;

    private User userLP;
    private User userMR;


    @BeforeAll
    public void init() {
        userLP = new User();
        userLP.setName("Lapo");
        userLP.setSurname("Pancani");
        userLP.setEmail("lpancani@info.com");
        userLP.setAddress("Via Pinco, Firenze");
        repository.save(userLP);

        userMR = new User();
        userMR.setName("Mario");
        userMR.setSurname("Rossi");
        userMR.setEmail("mrossi@info.com");
        userMR.setAddress("Via Pallino, Firenze");
        repository.save(userMR);
    }

    @Test
    public void test01searchWith_Surname() {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("surname", ":", "pancani"));

        List<User> results = repository.findAll(spec);

        assertThat(userLP, is(in(results)));
        assertThat(userMR, not(is(in(results))));
        Assertions.assertThat(userMR).isNotIn(results);
    }

    @Test
    public void test02searchWith_Name() {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("name", ":", "ArI"));

        List<User> results = repository.findAll(spec);

        assertThat(userLP, not(is(in(results))));
        assertThat(userMR, is(in(results)));
    }

    @Test
    public void test03searchWith_Name_And_Surname() {
        UserSpecification spec1 =
                new UserSpecification(new SearchCriteria("name", ":", "ArI"));
        UserSpecification spec2 =
                new UserSpecification(new SearchCriteria("surname", ":", "rossi"));

        List<User> results = repository.findAll(where(spec1).and(spec2));

        assertThat(userLP, not(is(in(results))));
        assertThat(userMR, is(in(results)));
    }

    private static Stream<Arguments> test04searchWith_SearchString() {
        return Stream.of(
                Arguments.of("name:\"apo\"~surname:\"anc\"", 1),
                Arguments.of("name:\"ar\"~surname:\"ss\"", 2),
                Arguments.of("name:\"ma\"", 3),
                Arguments.of("surname:\"ncani\"", 4),
                Arguments.of("", 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void test04searchWith_SearchString(String searchString, int index) {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        Pattern pattern = Pattern.compile(SearchCriteria.searchStringPattern);
        Matcher matcher = pattern.matcher(searchString);
        while (matcher.find()) {
            builder.addSearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<User> spec = builder.build();
        List<User> results = repository.findAll(spec);

        if (index == 1 || index == 4) {
            assertThat(userLP, is(in(results)));
            assertThat(userMR, not(is(in(results))));
        } else if (index == 2 || index == 3) {
            assertThat(userLP, not(is(in(results))));
            assertThat(userMR, is(in(results)));
        } else {
            assertThat(userLP, is(in(results)));
            assertThat(userMR, is(in(results)));
        }

    }*/
}
