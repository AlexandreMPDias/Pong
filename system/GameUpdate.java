package system;

import execution.DefaultValues;
import helper.Ponto;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import model.IElement;
import system.ObjectsHolder.ObjEnum;

public class GameUpdate implements Runnable {
  private int leftScore;
  private int rightScore;
  private boolean pauseGame;
  private boolean exit;
  private final double maxDispersion;
  private final double minDispersion;


  public GameUpdate() {
    leftScore = 0;
    rightScore = 0;
    pauseGame = false;
    exit = false;
    maxDispersion = 5*(Math.PI/360);
    minDispersion = (-1)* maxDispersion;
  }

  private Point2D getScore(){
    return new Ponto(leftScore, rightScore);
  }

  void addScoreLeft(){
    leftScore++;
  }

  void addScoreRight(){
    rightScore++;
  }

  void pause(boolean setPause){
    pauseGame = setPause;
  }

  void exit(){
    exit = true;
  }

  @Override
  public void run() {
    while(!exit) {
      if (!pauseGame) {
        long begin = System.nanoTime();
        IElement ball = ObjectsHolder.getElement(ObjEnum.Ball);
        if (ball != null) {
          ball.setLocation(SPD.run(ball));
          IElement collided = ball.collidedWith();
          if(collided != null){
            if(collided.getType() == ObjEnum.LeftBorder){
              ObjectsHolder.resetBall();
              rightScore++;
            }
            else if(collided.getType() == ObjEnum.RightBorder){
              ObjectsHolder.resetBall();
              leftScore++;
            }
            else {
              ball.setAngle(getAngle(ball));
            }
          }
        }
        long elapsedTime = System.nanoTime() - begin;
        sleepRemainingTime(elapsedTime, DefaultValues.threadMaxWaitTimeNanoSeconds);
      }
    }
  }

  private double getAngle(IElement ball){
    double angle = ball.getAngle() - Math.PI;
    if(angle <= (-2 * Math.PI)){
      angle += (2 * Math.PI);
    }
    else if(angle >= (2 * Math.PI)){
      angle -=  (2 * Math.PI);
    }
    return angle + ThreadLocalRandom.current().nextDouble(minDispersion,maxDispersion);
  }

  private void sleepRemainingTime(long elapsedRunTime, long maxTime){
    long sleepTime;
    if(elapsedRunTime > maxTime){
      return;
    }else{
      sleepTime = maxTime - elapsedRunTime;
    }
    try {
      Thread.sleep(0, (int)sleepTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
