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

  /**
   * Constructor.
   */
  public ParticleSimulation(String name, ParticlesModel m) {
    model = m;
    screen = new ParticlesView(name, m);
    queue = new MinPriorityQueue<>();
    queue.add(new Tick(1));
    Iterable<Collision> newCollisions = model.predictAllCollisions(clock);
    for (Collision col: newCollisions) {
      queue.add(col);
    }
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

    Event next = null;
    while(true) {
      next = queue.remove();
      if (next.isValid()) {
        double dt = next.time() - clock;
        clock = next.time();
        model.moveParticles(dt);
        next.happen(this);
      } else {
//        System.out.println(clock);
      }
    }
  }


  @Override
  public void reactTo(Tick tick) {
    try {
      Thread.sleep(FRAME_INTERVAL_MILLIS);
    } catch (InterruptedException e) {
//      System.out.println("kok");
      return;
    }
//    System.out.println("ok");
    screen.update();
    double nextTick = Math.floor(1 + clock);
    queue.add(new Tick(nextTick));
  }

  @Override
  public void reactTo(Collision c) {
//    System.out.println("col ok");
    screen.update();

    Iterable<Collision> newCollisions = model.predictAllCollisions(clock);
    for (Collision col: newCollisions) {
      queue.add(col);
    }

//    Particle[] ps = c.getParticles();
//    for (Particle p: ps) {
//      Iterable<Collision> newCollisions = model.predictCollisions(p, clock);
//      for (Collision col : newCollisions) {
//        queue.add(col);
//      }
//    }
  }
}
