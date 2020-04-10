package ch.LFG.entityService;

import ch.LFG.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ch.LFG.entityService.UserService userService;

    @Test
    void findAll() {
        final User user1 = new User(1, "Abrams", "william");

        final User user2 = new User(2, "Lord", "Wilhelm");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.getAll();

        assertEquals(expected, users);
    }

    //Future Tests

//    @Test
//    void createUser() {
//        final User user = new User(1L, "", "");
//
//        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
//        given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));
//
//        User savedUser = userservice.createUser(user);
//
//        assertThat(savedUser).isNotNull();
//
//        verify(userRepository).save(any(User.class));
//    }

//    @Test
//    void updateUser() {
//        final User user = new User(1L, "blah", "blu");
//
//        given (userRepository.save(user)).willReturn(user);
//
//        final User expected = userSerivce.updateUser(user);
//
//        assertThat(expected).isNotNull();
//        verify(userRepository).save(any(User.class));
//    }

//    @Test
//    void findUserById() {
//        final Long id = 1L;
//        final User user = new User(1L, "blah", "blu");
//
//        given (userRepository.findById(id)).willReturn(Optional.of(user));
//
//        final Optional<User> expected = userService.findUserById(id);
//        assertThat(expected).isNotNull();
//    }

//    @Test
//    void deleteUser() {
//        final Long userId = 1L;
//
//        userService.deleteUserById(userId);
//        userService.deleteUserById(userId);
//
//        verify ( userRepositry, times(2)).deleteById(userId);
//    }

//    @Test
//    void throwErrorWhenSaveWithExistingEmail() {
//        final User user = new User (1L, "bl", "bo");
//
//        given (userRepository.findById(user.getId())).willReturn(Optional.of(user));
//
//        assertThrows(UserRegistrationException.class, () -> {
//            userService.createUser(user);
//        });
//
//        verify(userRepository, never()).save(any(User.class));
//    }

}