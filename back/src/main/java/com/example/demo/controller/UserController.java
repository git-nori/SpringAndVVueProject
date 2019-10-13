package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.ResouceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    // HTTPステータスやコンテンツタイプ以外のレスポンスヘッダも指定したい場合 => 戻り値をResponseEntityにする
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long userId) throws ResouceNotFoundException {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResouceNotFoundException("User not found on => " + userId)); // 要素が取得できなかった場合、例外を投げる(GlobalExceptionHandlerで例外処理がかかる)
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User CreateUser(@Valid @RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @Valid @RequestBody User userDetails) throws ResouceNotFoundException{
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResouceNotFoundException("User not found on => " + userId));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmailId(userDetails.getEmailId());
        user.setUpdatedAt(new Date());
        final User updatedUser = repository.save(user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable("id") long userId) throws ResouceNotFoundException{
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResouceNotFoundException("User not found on => " + userId));

        repository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
