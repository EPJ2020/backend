package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @Test
    void getGroupProfile() {
        final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, new String[]{"powerbi", "dax", "m", "busniess intelligence"});

        Mockito.when(groupRepository.getOne(17L)).thenReturn(group);

        Appgroup savedGroup = groupService.getGroupProfile(17);

        assertEquals("DAX", savedGroup.getName());
    }

    @Test
    void updateGroupProfile() {
        final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, new String[]{"powerbi", "dax", "m", "busniess intelligence"});

        Mockito.when(groupRepository.save(group)).thenReturn(group);
        Mockito.when(groupRepository.getOne(17L)).thenReturn(group);

        Appgroup savedGroup = groupService.updateGroupProfile(group);

        assertEquals("DAX", savedGroup.getName());
    }

    @Test
    void deleteGroupProfileIllegalId() {
        final Appgroup group = new Appgroup(17, 1, "DAX", "dax@powerbi", "333333333", "Trying to learn DAX", true, new String[]{"powerbi", "dax", "m", "busniess intelligence"});

        doThrow(IllegalArgumentException.class).when(groupRepository).delete(group);
        Mockito.when(groupRepository.save(group)).thenReturn(group);
        boolean answer = groupService.deleteGroupProfile(group.getGroupId());

        assertEquals(false, answer);
    }
}