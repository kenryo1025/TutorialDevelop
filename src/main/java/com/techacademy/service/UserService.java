package com.techacademy.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository=repository;
    }

    /**全件検索を行う**/
    public List<User> getUserList(){
        return userRepository.findAll();
    }

    /**Userを1件検索して返す**/
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    /**Userの登録を行う**/
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
