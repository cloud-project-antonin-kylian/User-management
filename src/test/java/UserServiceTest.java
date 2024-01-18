import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.userManagement.Exceptions.UserNotFoundException;
import org.userManagement.entities.User;
import org.userManagement.repositories.UserRepository;
import org.userManagement.services.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @Test
    public void testCreateOneUser() {
        User newUser = new User(1, "John", "Doe", "JohnDoe@gm.com", "JohnDoe");

        when(userRepository.save(newUser)).thenReturn(newUser);
        UserService userService = new UserService(userRepository);

        User result = userService.createUser(newUser);

        assertEquals(newUser.getFirstName(), result.getFirstName());
        assertEquals(newUser.getLastName(), result.getLastName());
        verify(userRepository, times(1)).save(newUser);
    }
    @Test
    public void testCreateMultipleUser() {
        User newUser1 = new User(1, "John", "Doe");
        User newUser2 = new User(2, "David", "Johnson");
        User newUser3 = new User(3, "Lisa", "Marin");

        when(userRepository.save(newUser1)).thenReturn(newUser1);
        when(userRepository.save(newUser2)).thenReturn(newUser2);
        when(userRepository.save(newUser3)).thenReturn(newUser3);

        userService.createUser(newUser1);
        userService.createUser(newUser1);
        userService.createUser(newUser1);

        verify(userRepository , times(3)).save(any());
    }
    @Test
    public void testUpdateUser_ShouldUpdatedUserName() throws UserNotFoundException {
        User existingUser = new User(1, "John", "Doe");

        User updatedUser = new User(1,"Jane", "Doe");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        userService.createUser(existingUser);
        User result = userService.updateUser(updatedUser);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    public void testDeletedUser_ShouldDeletedUser2() throws UserNotFoundException {
        User user1 = new User(1, "John", "Doe");
        User user2 = new User(2, "David", "Johnson");
        User user3 = new User(3, "Lisa", "Marin");

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userRepository.findById(3)).thenReturn(Optional.of(user3));

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        userService.deleteUser(user2.getId());
        verify(userRepository, times(1)).deleteById(user2.getId());
        assertEquals(user1.getFirstName(), userRepository.findById(1)
                .orElseThrow(() -> new UserNotFoundException("Not found"))
                .getFirstName());
        assertEquals(user3.getFirstName(), userRepository.findById(3).orElseThrow(() -> new UserNotFoundException("Not found"))
                .getFirstName());
    }

    @Test
    public void testGetUserById_WhenUserExists_ShouldReturnUser() throws UserNotFoundException {
        User existingUser = new User(1, "John", "Doe", "john.doe@example.com", "password123");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        UserService userService = new UserService(userRepository);

        User result = userService.getUserById(1);

        assertEquals(existingUser, result);
    }

    @Test
    public void testGetUserById_WhenUserDoesNotExist_ShouldThrowUserNotFoundException() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1));
    }


    @Test
    public void testGetAllUser_ShouldReturn5User(){
        User user1 = new User(1, "John", "Doe");
        User user2 = new User(2, "David", "Johnson");
        User user3 = new User(3, "Lisa", "Marin");

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        List<User> userList = userRepository.findAll();

        assertEquals(3, userList.size());
        verify(userRepository, times(1)).findAll();
    }
}
