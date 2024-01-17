package org.userManagement.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.userManagement.Exceptions.UserNotFoundException;
import org.userManagement.entities.User;
import org.userManagement.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public User createUser(User user) {
        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return userRepository.save(newUser);
    }

    public User updateUser( User user) throws UserNotFoundException {
        //User existingUser = (User) userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + user.getId()));
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with ID: " + user.getId());
        }
    }

    public void deleteUser(int userId) throws UserNotFoundException {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }
}
