package simulation;

public abstract class Collision extends AbstractEvent{

    private final Particle[] particles;
    private int initialHits = 0;
    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        super(t);
        particles = ps;
        initialHits = getParticleHits();
    }

    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
    public boolean isValid() {
        int updatedHits = getParticleHits();
        return (initialHits == updatedHits);
    }

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;
    }

    private int getParticleHits() {
        int hits = 0;
        for (int i = 0; i < particles.length; i++) {
            hits += particles[i].collisions();
        }
        return hits;
    }
}
