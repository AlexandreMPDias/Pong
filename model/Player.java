package model;

import execution.DefaultValues;
import system.ObjectsHolder.ObjEnum;

public class Player extends Element implements IElement {

  private double position;
  private static double minPos;
  private static double maxPos;
  private final double spd;

  public Player(ObjEnum obj){
    super(obj);
    position = 0;
    minPos = 0;
    maxPos = DefaultValues.screenSize.getHeight()/2.0;
    spd = 10.0;
  }
  public void resetPosition(){
    position = 0;
  }

  public void moveUp(){
    if(this.position > minPos) {
      if(this.getLocation().getY() > 0) {
        moveUp(spd);
      }
    }

  }

  private void moveUp(double speed){
    for(int i = 0; i < speed; i++) {
      if (this.position > minPos) {
        if (this.getLocation().getY() > 0) {
          position--;
        }
      }
    }
  }

  public void moveDown(){
    if(this.position <= maxPos) {
      if(this.getLocation().getY() + this.getSize().getY() < (maxPos * 2.0)) {
        moveDown(spd);
      }
    }
  }

  private void moveDown(double speed){
    for(int i = 0; i < speed; i++) {
      if(this.position <= maxPos) {
        if(this.getLocation().getY() + this.getSize().getY() < (maxPos * 2.0)) {
          position++;
        }
      }
    }
  }

  public static void setRange(double maxPosition){
    maxPos = maxPosition;
    minPos = -maxPosition;
  }



  public double getPosition(){
    return position;
  }

}
