package view;

import execution.DefaultValues;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.IElement;

public class MainFrame implements IView {
  private JFrame main;
  private ViewPanel panel;

  public MainFrame(){
    main = new JFrame();
    panel = new ViewPanel();
    panel.setPreferredSize(DefaultValues.screenSize);
    main.setMinimumSize(DefaultValues.minimumScreenSize);
    main.setTitle("Pong");

    main.add(panel);
    main.setResizable(true);
    main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    main.setLocationRelativeTo(null);
    main.setVisible(true);
    main.pack();
  }

  public Dimension getScreenSize(){
    return panel.getSize();
  }

  public void refresh(){
    panel.repaint();
    main.repaint();
  }

  public void addListener(KeyListener key){
    main.addKeyListener(key);
  }

  public void drawElements(List<IElement> elements){
    panel.drawElements(elements);
  }

  public void update(int pos){
    panel.updatePosition(pos);
  }

  public void quit(){
    main.removeAll();
    main.dispose();
  }
}
