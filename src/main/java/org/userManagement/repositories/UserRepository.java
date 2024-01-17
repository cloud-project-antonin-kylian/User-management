package org.userManagement.repositories;

import org.springframework.stereotype.Repository;
import org.userManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
    }

    public int getNumberUsers() {
        return users.size();
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
    }

    public User updated(User userUpdated) {
        Optional<User> userOptional = findById(userUpdated.getId());

        if(userOptional.isPresent()){
            User user = userOptional.get();

            if (!userUpdated.getFirstName().equals(user.getFirstName())) {
                user.setFirstName(userUpdated.getFirstName());
            }
            if (!userUpdated.getLastName().equals(user.getLastName())) {
                user.setLastName(userUpdated.getFirstName());
            }
            if (!userUpdated.getEmail().equals(user.getEmail())){
                user.setEmail(userUpdated.getEmail());
            }
            if (!userUpdated.getPassword().equals(user.getPassword())){
                user.setPassword(userUpdated.getPassword());
            }
            return user;
        }
        return userUpdated;
    }

    public User save(User newUser) {
        users.add(newUser);
        return newUser;
    }

    public void deleteById(int userId) {
        users.removeIf(user -> user.getId()== userId);
    }
}
