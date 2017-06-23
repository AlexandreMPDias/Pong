package helper;

import java.awt.geom.Point2D;

public class PontoR4 {
  private double x;
  private double y;
  private double w;
  private double h;

  PontoR4(double x, double y, double width, double height){
    this.x = x;
    this.y = y;
    this.w = width;
    this.h = height;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public double getWidth(){
    return w;
  }

  public double getHeight(){
    return h;
  }

  public Point2D getPoint(){
    return new Ponto(x,y);
  }

}
