package control;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_UP;

import control.KeyHandler.KeyEventType;
import system.GameUpdate;
import system.ObjectsHolder;
import view.MainFrame;

public class Control {
  private MainFrame view;
  private GameUpdate update;


  public Control(){
    view = new MainFrame();
    view.addListener(createKeys());
    view.drawElements(ObjectsHolder.getAllElement());
    update = new GameUpdate();
  }

  public void start(){
    Thread th = new Thread(update);
    th.start();
    while(true){
      ObjectsHolder.rescaleElements(view.getScreenSize());
      view.refresh();
    }
  }

  private KeyHandler createKeys(){
    KeyHandler key = new KeyHandler();

    Runnable one = () -> {
      ObjectsHolder.updatePlayer(0,false);
    };
    Runnable two = () -> {
      ObjectsHolder.updatePlayer(0,true);
    };

    key.addKeyCommand(KeyEventType.KeyPressed, VK_UP, one);
    key.addKeyCommand(KeyEventType.KeyPressed, VK_DOWN, two);

    return key;
  }


}
