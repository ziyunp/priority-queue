package simulation;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;

public class TwoParticleCollision extends Collision {

    /**
     * Constructor for Collision
     */
    public TwoParticleCollision(double t, Particle[] ps) {
        super(t, ps);
    }

    @Override
    public void happen(ParticleEventHandler h) {
        Particle[] particles = getParticles();
        Particle p1 = particles[0];
        Particle p2 = particles[1];
        p1.collide(p1, p2);
        h.reactTo(this);
    }
}
