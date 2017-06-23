package model;

import helper.Ponto;
import system.ObjectsHolder.ObjEnum;
import system.Physic;

public class Element extends Physic implements IElement {

  boolean visible;
  volatile double speed;
  volatile double angle;
  final ObjEnum type;

  public Element(ObjEnum type) {
    visible = true;
    speed = 0.0;
    angle = 0.0;
    this.type = type;
    this.setObj(type);
  }

  Element(IElement e){
    visible = e.isVisible();
    speed = e.getSpeed();
    angle = e.getAngle();
    type = e.getType();
    setLocation(e.getLocation());
    setSize(new Ponto(e.getDimensions().getWidth(), e.getDimensions().getHeight()));
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public synchronized void setSpeed(double speed) {
    this.speed = speed;
  }

  @Override
  public synchronized double getSpeed() {
    return this.speed;
  }

  @Override
  public synchronized  void setAngle(double angle) {
    this.angle = angle;
  }

  @Override
  public synchronized double getAngle() {
    return this.angle;
  }

  @Override
  public ObjEnum getType(){
    return type;
  }
}
