package simulation;

public abstract class Collision extends AbstractEvent {

  private final Particle[] particles;
  int particleHits;

  /**
   * Constructor for Collision
   */
  public Collision(double t, Particle[] ps) {
    super(t);
    this.particles = ps;
    for (int i = 0; i < ps.length; i++) {
      particleHits += ps[i].collisions();
    }
  }

  /**
   * Returns true if this Collision is (still) valid.
   */
  @Override
  public boolean isValid() {
    int curHits = 0;
    for (int i = 0; i < particles.length; i++) {
      curHits += particles[i].collisions();
    }
    return curHits == particleHits;
  }

  /**
   * Returns an array containing the Particles involved in this Collision.
   */
  public Particle[] getParticles() {
    return particles;
  }
}
