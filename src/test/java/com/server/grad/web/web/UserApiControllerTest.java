package com.server.grad.web.web;

import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;
import com.server.grad.dto.user.UserSaveRequestDto;
import com.server.grad.dto.user.UserUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception{
        userRepository.deleteAll();
    }

    @Test
    public void insertUser() throws Exception{
        String name = "이름";
        String role = "mom";
        String email = "dlapdlf@";
        Long family_code = 11L;

        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .name(name)
                .email(email)
                .role(role)
                .build();

        String url = "http://localhost:" + port + "/user";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
    }

    @Test
    public void updateUser() throws Exception {
        User savedUser = userRepository.save(User.builder().name("user2").role("papa").email("go@gle").build());

        Object updateId = savedUser.getId();
        String expectedName = "u3";
        String expectedRole ="son";
        String expectedEmail = "Na@ver";
        Long expectedFamilycode = 13l;

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder().name(expectedName).role(expectedRole).email(expectedEmail).build();

        String url = "http://localhost:" + port + "/user/" + updateId;

        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);

    }

}
