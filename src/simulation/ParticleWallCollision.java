package simulation;

public class ParticleWallCollision extends Collision {

  private final Wall wall;

  /**
   * Constructor for Collision
   *
   * @param t
   * @param ps
   */
  public ParticleWallCollision(double t, Particle[] ps, Wall wall) {
    super(t, ps);
    this.wall = wall;
  }

  @Override
  public void happen(ParticleEventHandler h) {
//    should there be more than one?
    for(int i = 0; i < getParticles().length; i++) {
      Particle particle = getParticles()[i];
      particle.collide(particle, wall);
    }
    h.reactTo(this);
  }
}
