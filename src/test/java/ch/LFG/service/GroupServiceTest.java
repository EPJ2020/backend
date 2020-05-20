package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

  @Mock
  private GroupRepository groupRepository;
  @Mock
  private MatcherService matcherService;

  @InjectMocks
  private GroupService groupService;

  @Test
  void getGroupProfile() {
    final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});

    when(groupRepository.getOne(17L)).thenReturn(group);

    Appgroup savedGroup = groupService.getGroupProfile(17);

    assertEquals("DAX", savedGroup.getName());
  }

  @Test
  void getGroupProfileInvalidId() {
    when(groupRepository.getOne(99L)).thenThrow(new NullPointerException());
    assertThrows(NullPointerException.class, () -> {
      groupService.getGroupProfile(99);
    });
  }

  @Test
  void setGroupProfile() {
    final Appgroup group = new Appgroup(4, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});

    when(groupRepository.getOne(4L)).thenThrow(new NullPointerException());
    when(groupRepository.save(group)).thenReturn(group);
    doNothing().when(matcherService).initializeAllMatchSuggestion(group);

    Appgroup savedGroup = groupService.setGroupProfile(group);

    assertEquals("DAX", savedGroup.getName());
  }

  @Test
  void setGroupProfileInvalid() {
    final Appgroup group = new Appgroup(4, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});
    when(groupRepository.getOne(4L)).thenReturn(group);

    assertThrows(NullPointerException.class, () -> {
      groupService.setGroupProfile(group);
    });
  }

  @Test
  void updateGroupProfile() {
    final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});

    when(groupRepository.save(group)).thenReturn(group);
    when(groupRepository.getOne(17L)).thenReturn(group);

    Appgroup savedGroup = groupService.updateGroupProfile(group);

    assertEquals("DAX", savedGroup.getName());
  }

  @Test
  void updateGroupProfileInvalidId() {
    final Appgroup group = new Appgroup(99, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});

    when(groupRepository.getOne(99L)).thenThrow(new NullPointerException());

    assertThrows(NullPointerException.class, () -> {
      groupService.updateGroupProfile(group);
    });
  }

  @Test
  void deleteGroupProfile() {
    final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});

    when(groupRepository.getOne(17L)).thenReturn(group);

    boolean answer = groupService.deleteGroupProfile(group.getGroupId());

    assertEquals(true, answer);
  }

  @Test
  void deleteGroupProfileIllegalId() {
    final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, "england", new String[]{"powerbi", "dax", "m", "busniess intelligence"});

    doThrow(IllegalArgumentException.class).when(groupRepository).delete(group);
    when(groupRepository.save(group)).thenReturn(group);
    boolean answer = groupService.deleteGroupProfile(group.getGroupId());

    assertEquals(false, answer);
  }

  //Matching Logic
  @Test
  void setMatchAnswer() {
    MatchAnswer input = new MatchAnswer();
    input.setUserId(2);
    input.setGroupId(3);
    input.setAnswer(true);

    doNothing().when(matcherService).setMatchAnswer(2L, 3L, true, 2);
    boolean answer = groupService.setMatchAnswer(input);
    assertEquals(true, answer);
  }

  @Test
  void setMatchAnswerInvalidId() {
    MatchAnswer input = new MatchAnswer();
    input.setUserId(99);
    input.setGroupId(3);
    input.setAnswer(true);

    doThrow(IllegalArgumentException.class).when(matcherService).setMatchAnswer(99L, 3L, true, 2);
    boolean answer = groupService.setMatchAnswer(input);
    assertEquals(false, answer);
  }
}