package com.server.grad.web.domain;

import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"security.basic.enabled=false"})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    public void user_test(){

        String name = "u1";
        String role = "mama";
        String email = "google@";
        Long family_code = 11l;

        userRepository.save(User.builder()
                .name(name)
                .role(role)
                .email(email)
                .family_code(family_code)
                .build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        assertThat(user.getName()).isEqualTo(name);

    }
}
