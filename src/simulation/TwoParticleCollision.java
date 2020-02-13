package simulation;

public class TwoParticleCollision extends Collision {

  /** Constructor for Collision */
  public TwoParticleCollision(Particle p1, Particle p2, double t) {
    super(t, new Particle[] {p1, p2});
  }

  @Override
  public void happen(ParticleEventHandler h) {

    Particle[] ps = getParticles();
    Particle p1 = ps[0];
    Particle p2 = ps[1];

    Particle.collide(p1, p2);

    h.reactTo(this);
  }
}
