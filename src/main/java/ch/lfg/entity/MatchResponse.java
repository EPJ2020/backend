package ch.lfg.entity;

/**
 * Represents an user or a group and the percentage of matching tags to another entity.
 * @param <T> Appgroup or Appuser
 */
public class MatchResponse<T> {

  private T object;
  private double percentage;

  public MatchResponse(T object, double percentage) {
    this.object = object;
    this.percentage = percentage;
  }

  /**
   * @return The object contained in MatchResponse
   */
  public T getObject() {
    return object;
  }

  /**
   * Change the object associated to this MatchResponse
   * @param object The new object for MatchResponse
   */
  public void setObject(T object) {
    this.object = object;
  }

  /**
   * @return the percentage of overlapping tags with the target object
   */
  public double getPercentage() {
    return percentage;
  }

  /**
   * Change the percentage of the login
   * @param percentage the new percentage
   */
  public void setPercentage(double percentage) {
    this.percentage = percentage;
  }
}
