package com.example.socialdemo.service;

import com.example.socialdemo.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private static List<User> userList = new ArrayList<>();
    static {
        userList.add(User.builder().name("Ankan").id(1).birthDate(LocalDate.now().minusYears(27)).build());
        userList.add(User.builder().name("Ankita").id(2).birthDate(LocalDate.now().minusYears(31)).build());
        userList.add(User.builder().name("Asima").id(3).birthDate(LocalDate.now().minusYears(60)).build());

    }
    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User save(User user) {
        User tempUser = User.builder().id(user.getId()).name(user.getName()).birthDate(user.getBirthDate()).build();
        userList.add(tempUser);
        return tempUser;
    }


    @Override
    public Optional<User> findById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findFirst();
    }

    public void deleteById(int id) {
        userList.removeIf(user -> user.getId() == id);
    }
}
