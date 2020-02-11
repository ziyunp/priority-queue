package simulation;

public class TwoParticleCollision extends Collision {

  /**
   * Constructor for Collision
   */
  public TwoParticleCollision(Particle p1, Particle p2, double t) {
    super(t, new Particle[]{p1, p2});
  }

  @Override
  public void happen(ParticleEventHandler h) {

    Particle p1 = getParticles()[0];
    Particle p2 = getParticles()[1];

    p1.collide(p1, p2);

    h.reactTo(this);
  }
}
