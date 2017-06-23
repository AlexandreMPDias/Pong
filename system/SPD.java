package system;

import helper.Ponto;
import model.IElement;

class SPD {
  static Ponto run(IElement element){
    double x = element.getLocation().getX() + element.getSpeed() * Math.cos(element.getAngle());
    double y = element.getLocation().getY() + element.getSpeed() * Math.sin(element.getAngle());
    return new Ponto(x,y);
  }

}
