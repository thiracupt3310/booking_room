package com.example.Booking.room.service;


import com.example.Booking.room.data.UserRepository;
import com.example.Booking.room.model.Transaction;
import com.example.Booking.room.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private RestTemplate restTemplate;
    private UserRepository repository;

    public UserService(RestTemplate restTemplate, UserRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }


    public void createUser(User user) {
        String hashPass = hash(user.getPassW());
        user.setPassW(hashPass);
        repository.save(user);
    }

    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin, salt);
    }


    public List<User> getUser() {

        return repository.findAll();
    }

    public User findUser(String username) {
        String url = "http://localhost:8091/api/user/" + username;
        ResponseEntity<User> response =
                restTemplate.getForEntity(url, User.class);
        return response.getBody();
    }

    public User checkPassword(User inputUser) {

        String url = "http://localhost:8091/api/user/" + inputUser.getUsername();
        ResponseEntity<User> response =
                restTemplate.getForEntity(url, User.class);

        User user = response.getBody();

        // 1. หา User ที่มี Username ตรงกับพารามิเตอร์
        User storeduser = findUser(inputUser.getUsername());

        // 2. ถ้ามี Username ตรง ให้เช็ค passw ว่าตรงกันไหม โดยใช้ฟังก์ชันเกี่ยวกับ hash
        if (storeduser != null) {
            String hashPin = storeduser.getPassW();

            if (BCrypt.checkpw(inputUser.getPassW(), hashPin))
                return storeduser;
        }
        // 3. ถ้าไม่ตรง ต้องคืนค่า null
        return null;
    }

}
