package simulation;

public abstract class AbstractEvent implements Event {

  private final double time;

  /**
   * Constructor for AbstractEvent.
   */
  public AbstractEvent(double time) {
    this.time = time;
  }

  /**
   * Returns the time (in ticks) at which this event will occur.
   */
  @Override
  public double time() {
    return time;
  }

  /**
   * Compares this object with the specified Event.
   */
  @Override
  public int compareTo(Event that) {
    if (this.time < that.time())
      return -1;
    if (this.time > that.time())
      return 1;
    return 0;
  }

}
