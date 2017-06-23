package helper;

import java.awt.Dimension;
import java.awt.geom.Point2D;

public class Ponto extends Point2D{
  private double x;
  private double y;

  public Ponto(double x, double y){
    this.x = x;
    this.y = y;
  }

  public Ponto(Dimension d){
    this.x = d.getWidth();
    this.y = d.getHeight();
  }

  Ponto(int x, int y){
    this.x = (double)x;
    this.y = (double)y;
  }

  Ponto(Point2D p){
    this.x = p.getX();
    this.y = p.getY();
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public void setLocation(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public int x(){
    return (int)x;
  }

  public int y(){
    return (int)y;
  }

  @Override
  public String toString(){
    return "Ponto: [" + x + "," + y + "]";
  }

  public String diff(Point2D p){
    return (p.getY() - this.getY()) + " :: " + (p.getX() - this.getX());
  }

  public void set(double x, double y){
    this.x = x;
    this.y = y;
  }
}
