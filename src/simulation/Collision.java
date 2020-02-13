package simulation;

public abstract class Collision extends AbstractEvent {

  private final Particle[] particles;
  private int particleHits;

  /** Constructor for Collision */
  public Collision(double t, Particle[] ps) {
    super(t);
    particles = ps;
    particleHits = getParticleHits();
  }

  /** Returns true if this Collision is (still) valid. */
  @Override
  public boolean isValid() {
    int curHits = getParticleHits();
    return curHits == particleHits;
  }

  /** Returns an array containing the Particles involved in this Collision. */
  public Particle[] getParticles() {
    return particles;
  }

  private int getParticleHits() {
    int hits = 0;
    for (Particle p : particles) {
      hits += p.collisions();
    }
    return hits;
  }
}
