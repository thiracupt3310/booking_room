package com.example.Booking.room.service;


import com.example.Booking.room.data.UserRepository;
import com.example.Booking.room.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
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
        try{
            return repository.findByUsername(username);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User checkPassword(User inputUser) {
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
