package execution;

import java.awt.Dimension;

public class DefaultValues {
  public final static Dimension screenSize = new Dimension(700 ,350);
  public final static Dimension minimumScreenSize = new Dimension(200,100);
  public final static double ballStartingSpeed = 0.5;// * (1.0 / 10000.0);

  public final static long threadMaxWaitTimeNanoSeconds = 5000L;
}
