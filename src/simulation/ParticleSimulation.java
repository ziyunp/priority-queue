package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler {

  private static final long FRAME_INTERVAL_MILLIS = 40;

  private final ParticlesModel model;
  private final ParticlesView screen;
  private MinPriorityQueue<Event> queue;
  private double clock = 0;

  /** Constructor. */
  public ParticleSimulation(String name, ParticlesModel m) {
    model = m;
    screen = new ParticlesView(name, m);
    queue = new MinPriorityQueue<>();
    queue.add(new Tick(1));
    m.predictAllCollisions(clock).forEach(collision -> queue.add(collision));
  }

  /** Runs the simulation. */
  @Override
  public void run() {
    try {
      SwingUtilities.invokeAndWait(screen);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    while (queue.size() > 0) {
      Event e = queue.remove();
      if (e.isValid()) {
        double dt = e.time() - clock;
        clock = e.time();
        model.moveParticles(dt);
        e.happen(this);
      }
    }
  }

  @Override
  public void reactTo(Tick tick) {
    try {
      Thread.sleep(FRAME_INTERVAL_MILLIS);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return;
    }
    screen.update();
    double nextTick = Math.floor(1 + clock);
    queue.add(new Tick(nextTick));
  }

  @Override
  public void reactTo(Collision c) {
    screen.update();
    Particle[] ps = c.getParticles();
    for (Particle p : ps) {
      model.predictCollisions(p, clock).forEach(collision -> queue.add(collision));
    }
  }
}
