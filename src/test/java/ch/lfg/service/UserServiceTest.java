package ch.lfg.service;

import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @LocalServerPort
  private int port;

  @Mock
  private UserRepository userRepository;
  @Mock
  private MatcherService matcherService;

  @InjectMocks
  private UserService userService;

  @Test
  void getUserProfile() {

    final Appuser user = new Appuser(17,17, "Mustang", "Roy", "colonelAsshole@amestris", "333333333", "I got sent into active duty once and now the government is overthrown", true,  35, "male", new String[]{"fire", "alchemy", "miniskirts", "me for president"});


    when(userRepository.getOne(17L)).thenReturn(user);

    Appuser savedUser = userService.getUserProfile(17);

    assertEquals("Mustang", savedUser.getLastName());
  }

  @Test
  void getUserProfileInvalidId() {
    when(userRepository.getOne(99L)).thenThrow(new NullPointerException());
    assertThrows(NullPointerException.class, () -> {
      userService.getUserProfile(99);
    });
  }

  @Test
  void updateUserProfile() {

    final Appuser user = new Appuser(17, 17, "Mustang", "Roy", "colonelAsshole@amestris", "333333333", "I got sent into active duty once and now the government is overthrown", true,  35, "male",  new String[]{"fire", "alchemy", "miniskirts", "me for president"});

    when(userRepository.getOne(17L)).thenReturn(user);
    when(userRepository.save(user)).thenReturn(user);

    Appuser savedUser = userService.updateUserProfile(user);

    assertEquals("Roy", savedUser.getFirstName());
  }

  @Test
  void updateUserProfileNull() {
    assertThrows(NullPointerException.class, () -> {
      userService.updateUserProfile(null);
    });
  }

  @Test
  void updateUserProfileUserDoesntExists() {
    final Appuser user = new Appuser(18, 17, "Mustang", "Roy", "colonelAsshole@amestris", "333333333", "I got sent into active duty once and now the government is overthrown", true,  35, "male",  new String[]{"fire", "alchemy", "miniskirts", "me for president"});
    when(userRepository.getOne(18L)).thenThrow(new NullPointerException());
    assertThrows(NullPointerException.class, () -> {
      userService.updateUserProfile(user);
    });
  }

  @Test
  void getMyGroups() {

    final Appuser user = new Appuser(4, 4, "Mustang", "Roy", "colonelAsshole@amestris", "333333333", "I got sent into active duty once and now the government is overthrown", true,  35, "male",  new String[]{"fire", "alchemy", "miniskirts", "me for president"});

    when(userRepository.getOne(4L)).thenReturn(user);

    assertEquals(user.getGroups(), userService.getMyGroups(4L));
  }

  @Test
  void setMatchAnswer() {
    MatchAnswer input = new MatchAnswer();
    input.setUserId(2);
    input.setGroupId(3);
    input.setAnswer(true);

    doNothing().when(matcherService).setMatchAnswer(2L, 3L, true, 1);
    boolean answer = userService.setMatchAnswer(input);
    assertEquals(true, answer);
  }

  @Test
  void setMatchAnswerInvalidId() {
    MatchAnswer input = new MatchAnswer();
    input.setUserId(99);
    input.setGroupId(3);
    input.setAnswer(true);

    doThrow(IllegalArgumentException.class).when(matcherService).setMatchAnswer(99L, 3L, true, 1);
    boolean answer = userService.setMatchAnswer(input);
    assertEquals(false, answer);
  }
}