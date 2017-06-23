package view;

import helper.Ponto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import model.IElement;

public class ViewPanel extends JComponent {
  private int position;
  private List<IElement> elements;


  ViewPanel(){
    position = 100;
    elements = new ArrayList<>();
  }


  @Override
  public void paint(Graphics g){
      paintBackground(g);
      g.setColor(Color.GRAY);
      for (IElement e : elements) {
        if(e.isVisible()) {
          Ponto p = new Ponto(e.getLocation().getX(),e.getLocation().getY());
          g.fillRect(p.x(), p.y(), e.getDimensions().width, e.getDimensions().height);
        }
      }
  }

  void updatePosition(int position){
    this.position = position * 10;
    if(this.position <= 0){
      this.position = 1;
    }
  }

  void drawElements(List<IElement> elements){
    if(elements != null){
      this.elements = elements;
    }
  }

  private void paintBackground(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(0,0, super.getWidth(), super.getHeight());
  }

}
