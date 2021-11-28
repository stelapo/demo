package com.example.demo.searchTest;

import com.example.demo.model.SearchCriteria;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.not;

@DataJpaTest
public class SpecificationsTest {

    @Autowired
    UserRepository repository;

    private User userLP;
    private User userMR;


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
        userLP.setAddress("Via Pallino, Firenze");
        repository.save(userMR);
    }

    @Test
    public void test01searchWithSurname() {
        init();
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("surname", ":", "pancani"));

        List<User> results = repository.findAll(spec);

        assertThat(userLP, is(in(results)));
        assertThat(userMR, not(is(in(results))));
    }
}
