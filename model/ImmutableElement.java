package model;

import system.ObjectsHolder.ObjEnum;

public class ImmutableElement extends Element implements IElement {

  public ImmutableElement(ObjEnum type) {
    super(type);
    visible = true;
    speed = 0.0;
    angle = 0.0;
  }

  public ImmutableElement(Element e){
    super(e.getType());
    visible = e.isVisible();
    speed = e.getSpeed();
    angle = e.getAngle();
    setLocation(e.getLocation());
    setSize(e.getSize());
  }

  @Override
  public void setVisible(boolean visible) {
    throw new IllegalArgumentException("This class can't be changed.");
  }

  @Override
  public synchronized void setSpeed(double speed) {
    throw new IllegalArgumentException("This class can't be changed.");
  }

  @Override
  public synchronized  void setAngle(double angle) {
    throw new IllegalArgumentException("This class can't be changed.");
  }
}
