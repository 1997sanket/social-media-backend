package com.kamble.user;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int idCounter = 0;

    static {
        users.add(new User(++idCounter, "Jim Halpert", LocalDate.now().minusYears(30)));
        users.add(new User(++idCounter, "Pam Beesly", LocalDate.now().minusYears(25)));
        users.add(new User(++idCounter, "Michael Scott", LocalDate.now().minusYears(40)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User saveUser(User user) {
        user.setId(++idCounter);
        users.add(user);
        return user;
    }

    public void deleteUserById(int id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
