package model;


import java.awt.Dimension;
import java.awt.geom.Point2D;
import system.ObjectsHolder.ObjEnum;

public interface IElement {
  boolean isVisible();
  void setVisible(boolean visible);

  void setSpeed(double spd);
  double getSpeed();

  void setAngle(double angle);
  double getAngle();

  void setSize(Point2D size);
  void setLocation(Point2D point);

  Dimension getDimensions();
  Point2D getLocation();

  ObjEnum getType();

  IElement collidedWith();
}
