package simulation;

public class TwoParticleCollision extends Collision {

  /**
   * Constructor for Collision
   *
   * @param t
   * @param ps
   */
  public TwoParticleCollision(double t, Particle[] ps) {
    super(t, ps);
  }

  @Override
  public void happen(ParticleEventHandler h) {

    Particle p1 = getParticles()[0];
    Particle p2 = getParticles()[1];

    p1.collide(p1, p2);

    h.reactTo(this);
  }
}
