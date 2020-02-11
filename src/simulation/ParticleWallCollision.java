package simulation;

public class ParticleWallCollision extends Collision {

  private final Wall wall;

  /**
   * Constructor for Collision
   */
  public ParticleWallCollision(Particle p, Wall w, double t) {
    super(t, new Particle[]{p});
    this.wall = w;
  }

  @Override
  public void happen(ParticleEventHandler h) {

    Particle particle = getParticles()[0];
    particle.collide(particle, wall);

    h.reactTo(this);
  }
}
