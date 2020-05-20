package ch.lfg.entity;

/**
 * Represents a MatchAnswer with the necessary details to update a Match
 */
public class MatchAnswer {
  private long userId;
  private long groupId;
  private Boolean answer;

  public MatchAnswer() {
  }

  /**
   * @return The Identifier of an user.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Change the identifier of the involved user
   * @param userId The identifier of the new user
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * @return The identifier of an group.
   */
  public long getGroupId() {
    return groupId;
  }

  /**
   * Change the identifier of the involved group
   * @param groupId The identifier of the new group
   */
  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  /**
   * @return The willingness of one of the participants
   */
  public Boolean getAnswer() {
    return answer;
  }

  /**
   * Change the status of the answer
   * @param answer The new status
   */
  public void setAnswer(Boolean answer) {
    this.answer = answer;
  }
}
