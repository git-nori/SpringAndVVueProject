package com.example.demo;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.domain.model.User;

@RunWith(SpringRunner.class) // テストの実行をするクラスの指定(SpringRunnerクラス => SpringJUnit4ClassRunnerクラスと同一の機能を持つ)
@SpringBootTest(classes = BackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // SpringBootの機能を使えるようにする
class BackApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    // テストに使用するポートをRandomにしているためポート番号を取得する
    @LocalServerPort
    private int port;

    // portを含めたRootUrlを返す
    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users", HttpMethod.GET, entity,
                String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
        System.out.println(user.getFirstName());

        assertNotNull(user);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmailId("admin@gmail.com");
        user.setCreeatedBy("admin");
        user.setUpdatedBy("admin");

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
        user.setFirstName("admin1");
        user.setLastName("admin2");

        restTemplate.put(getRootUrl() + "/users/" + id, user);

        User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);

        assertNotNull(updatedUser);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(user);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
