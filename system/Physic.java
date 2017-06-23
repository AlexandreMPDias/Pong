package system;

import helper.Ponto;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import model.IElement;
import system.ObjectsHolder.ObjEnum;

public abstract class Physic implements IElement {

  private Point2D size;
  private Point2D location;
  private ObjEnum thisObject;

  @Override
  public void setSize(Point2D d){
    this.size = d;
  }

  protected void setObj(ObjEnum obj){
    thisObject = obj;
  }

  @Override
  public void setLocation(Point2D location){
    this.location = location;
  }

  public Point2D getSize(){
    if(size == null){
      throw new IllegalArgumentException("Size not Set.");
    }
    return size;
  }

  @Override
  public Point2D getLocation(){
    if(location == null){
      throw new IllegalArgumentException("Location not Set.");
    }
    return location;
  }

  @Override
  public IElement collidedWith(){
    for(IElement e : ObjectsHolder.getAllElement()){
      if(e.getType() != ObjEnum.Ball)
      if(checkCollisionWith(e)){
        return e;
      }
    }
    return null;
  }

  private boolean checkCollisionWith(IElement object){
    double w = object.getDimensions().getWidth();
    double h = object.getDimensions().getHeight();
    double x = object.getLocation().getX();
    double y = object.getLocation().getY();

    if(size.getX() + location.getX() >= x && size.getX() + location.getX() <= x + w){
      if(size.getY() + location.getY() >= y && size.getY() + location.getY() <= y + h){
        return true;
      }
    }
    if(x + w >= location.getX() && x + w <= location.getX() + size.getX()){
      if((y + h) >= location.getY() && (y + h) <= location.getY() + size.getY()){
        return true;
      }
    }
    return false;
  }

  public Position collidesWith(IElement object){
    double w = 0.5 * (this.size.getX() + object.getDimensions().getWidth());
    double h = 0.5 * (this.size.getY()+ object.getDimensions().getHeight());
    double dx = (center(this).getX()- center(object).getX());
    double dy = (center(this).getY()- center(object).getY());
    if (Math.abs(dx) <= w && Math.abs(dy) <= h)
    {
    /* collision! */
      double wy = w * dy;
      double hx = h * dx;

      if (wy > hx) {
        if (wy > -hx) {
            return Position.TOP;
        } else {
            return Position.LEFT; /* on the left */
        }
      }
    else{
          if (wy > -hx) {
            return Position.RIGHT; /* on the right */
          } else {
            return Position.BOTTOM;/* at the bottom */
          }
        }
    }
    return null;
  }

  public boolean contains(Point2D p){
    if(p.getX() > location.getX() && p.getX() < (location.getX() + size.getX())){
      if(p.getY() > location.getY() && p.getY() < (location.getY() + size.getY())){
        return true;
      }
    }
    return false;
  }

  private Point2D center(IElement e){
    return new Ponto((e.getDimensions().getWidth()/2) + location.getX(),(e.getDimensions().getHeight()/2) + location.getY());
  }

  @Override
  public Dimension getDimensions(){
    return new Dimension((int)this.getSize().getX(), (int)this.getSize().getY());
  }
}
