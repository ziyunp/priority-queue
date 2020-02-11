package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;

    private final ParticlesModel model;
    private final ParticlesView screen;
    private MinPriorityQueue<Event> eventQueue;
    private double clock = 0.0;

    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
        model = m;
        screen = new ParticlesView(name, m);
        eventQueue = new MinPriorityQueue<>();
        eventQueue.add(new Tick(1));
    }

    /**
     * Runs the simulation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeAndWait(screen);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(eventQueue.size() > 0) {
            Event e = eventQueue.remove();
            double timeElapsed = e.time() - clock;
            clock = e.time();
            model.moveParticles(timeElapsed);
            e.happen(this);
        }
    }

    @Override
    public void reactTo(Tick tick) {
        try {
            Thread.sleep(FRAME_INTERVAL_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        screen.update();
        eventQueue.add(new Tick(clock + 1));
    }

    @Override
    public void reactTo(Collision c) {
        screen.update();
        Particle[] ps = c.getParticles();
        for (Particle p : ps) {
            model.predictCollisions(p, clock).forEach(collision -> eventQueue.add(collision));
        }
    }

//    private void updateClock(double time) {
//        clock = time;
//        model.predictAllCollisions(clock).forEach(collision -> eventQueue.add(collision));
//    }
}
