package simulation;

public class ParticleWallCollision extends Collision {

    private final Wall wall;

    /**
     * Constructor for Collision
     */
    public ParticleWallCollision(double t, Particle[] ps, Wall w) {
        super(t, ps);
        wall = w;
    }

    @Override
    public void happen(ParticleEventHandler h) {
        Particle particle = getParticles()[0];
        particle.collide(particle, wall);
        h.reactTo(this);
    }
}
