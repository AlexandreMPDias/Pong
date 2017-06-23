package system;

import execution.DefaultValues;
import helper.Ponto;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import model.Element;
import model.IElement;
import model.Player;

public class ObjectsHolder {

  static volatile private IElement bt, bl, br, bb;
  static volatile private IElement ball;
  static volatile private Player[] players;
  static volatile private Ponto screenSize;

  public enum ObjEnum {
    Ball, LeftBorder, RightBorder, TopBorder, BottomBorder, Player1, Player2
  }

  static {
    screenSize = new Ponto(DefaultValues.screenSize);
    createBall();
    for (Position p : Position.values()) {
      createBorder(p);
    }
    players = new Player[2];
    createPlayer(0);
    createPlayer(1);
  }

  /**
   * ALL
   */
  public static List<IElement> getAllElement() {
    return getElement(ObjEnum.Ball, ObjEnum.LeftBorder, ObjEnum.RightBorder,
        ObjEnum.TopBorder, ObjEnum.BottomBorder, ObjEnum.Player1, ObjEnum.Player2);
  }
  public static List<IElement> getElement(ObjEnum... object) {
    List<IElement> list = creator();
    if (object != null) {
      for (ObjEnum enums : object) {
        list.add(getElement(enums));
      }
      return list;
    }
    return null;
  }
  public static void rescaleElements(Dimension sizeOfScreen) {
    Ponto sizeNow = new Ponto(sizeOfScreen);
    if (sizeNow.getX() != screenSize.getX() || sizeNow.getY() != screenSize.getY()) {
      Point2D oldSize = screenSize;
      screenSize = sizeNow;

      //Ball
      ball.setSize(new Ponto(ballSize(), ballSize()));
      ball.setLocation(newLocation(ball.getLocation(), oldSize));

      //Border
      updateAllBorders();

      //Players
      for(int i = 0; i < 2; i++) {
        players[i].setSize(new Ponto(playerSize().getX(), playerSize().getY()));
        players[i].setLocation(new Ponto(playerLocation(i).getX(),
            newLocation(players[i].getLocation(), oldSize).getY()));
      }
      Player.setRange(playerPosition());

    }
  }
  public static void updatePlayer(int player, boolean up){
    double pos;
    if(!up){
      players[player].moveUp();
    }
    else{
      players[player].moveDown();
    }
    players[player]
        .setLocation(new Ponto(playerLocation(player).getX(), playerLocation(player).getY()));
  }

  public static void test(){
    System.out.println(players[0].getPosition() + " -- " + players[0].getLocation());
  }
  static IElement getElement(ObjEnum object) {
    switch (object) {
      case Ball:
        return ball;
      case Player1:
        return players[0];
      case Player2:
        return players[1];
      case LeftBorder:
        return bl;
      case RightBorder:
        return br;
      case TopBorder:
        return bt;
      case BottomBorder:
        return bb;
    }
    return null;
  }

  /**
   * BORDER
   */
  private static synchronized void createBorder(Position p) {
    int len = 5;
    int o = 0;
    switch (p) {
      case TOP: {
        bt = new Element(ObjEnum.TopBorder);
        bt.setLocation(new Ponto(o, o));
        bt.setSize(new Ponto(screenSize.getX(), len));
        bt.setVisible(true);
        break;
      }
      case LEFT: {
        bl = new Element(ObjEnum.LeftBorder);
        bl.setLocation(new Ponto(o, o));
        bl.setSize(new Ponto(len, screenSize.getY()));
        bl.setVisible(true);
        break;
      }
      case RIGHT: {
        br = new Element(ObjEnum.RightBorder);
        br.setLocation(new Ponto(screenSize.getX() - len, o));
        br.setSize(new Ponto(len, screenSize.getY()));
        br.setVisible(true);
        break;
      }
      case BOTTOM: {
        bb = new Element(ObjEnum.BottomBorder);
        bb.setLocation(new Ponto(o, screenSize.getY() - len));
        bb.setSize(new Ponto(screenSize.getX(), len));
        bb.setVisible(true);
        break;
      }
      default: {
        throw new IllegalArgumentException("No borders on that position");
      }
    }
  }
  private static void updateAllBorders() {
    double len = 5;
    double o = 0;
    double w = screenSize.getX();
    double h = screenSize.getY();
    bt.setLocation(new Ponto(o, o));
    bt.setSize(new Ponto(w, len));

    bl.setLocation(new Ponto(o, o));
    bl.setSize(new Ponto(len, h));

    br.setLocation(new Ponto(w - len, o));
    br.setSize(new Ponto(len, h));

    bb.setLocation(new Ponto(o, h - len));
    bb.setSize(new Ponto(w, len));
  }

  /**
   * BALL
   */
  private static double ballSize() {
    return (screenSize.getY() + screenSize.getX()) / 100.0;
  }
  private static void createBall() {
    double size = ballSize();
    ball = new Element(ObjEnum.Ball);
    ball.setLocation(new Ponto(screenSize.getX() / 2.0, screenSize.getY() / 2.0));
    ball.setSize(new Ponto(size, size));
    ball.setSpeed(DefaultValues.ballStartingSpeed);
    ball.setAngle(0.0);
  }
  static void updateBall(IElement elem) {
    if (elem.getType() == ObjEnum.Ball) {
      ball = elem;
    }
  }
  static void resetBall() {
    ball.setLocation(new Ponto(screenSize.getX() / 2.0, screenSize.getY() / 2.0));
    ball.setAngle(ThreadLocalRandom.current().nextDouble(Math.PI * (-2), Math.PI * 2));
    ball.setSpeed(DefaultValues.ballStartingSpeed);
  }

  /**
   * PLAYER
   */
  private static Point2D playerSize() {
    return new Ponto(screenSize.getX() / 20.0, screenSize.getY() / 2.0);
  }
  private static Point2D playerLocation(int player) {
    double xPos = 10.0;
    double yPos = ((screenSize.getY() - playerSize().getY()) / 2.0) + ((players[player].getPosition()));
    if (player == 0) {
      return new Ponto(screenSize.getX() / xPos, yPos);
    } else {
      return new Ponto(screenSize.getX() - (screenSize.getX() / xPos + playerSize().getX()), yPos);
    }
  }
  private static double playerPosition(){
    return screenSize.getY() - players[0].getSize().getY();
  }
  private static void createPlayer(int player) {
    if (player == 0) {
      players[player] = new Player(ObjEnum.Player1);
      players[player].setSize(new Ponto(playerSize().getX(), playerSize().getY()));
      players[player]
          .setLocation(new Ponto(playerLocation(player).getX(), playerLocation(player).getY()));
    } else {
      players[player] = new Player(ObjEnum.Player2);
      players[player]
          .setLocation(new Ponto(playerLocation(player).getX(), playerLocation(player).getY()));
      players[player].setSize(new Ponto(playerSize().getX(), playerSize().getY()));
    }
    Player.setRange(playerPosition());
  }
  private static void updatePlayers(int player){
    players[player]
        .setLocation(new Ponto(playerLocation(player).getX(), playerLocation(player).getY()));
  }

  private static IElement getImmutableCopy(IElement element) {
    return null;
  }
  private static List<IElement> getListOfImmutableCopy(IElement element) {
    return null;
  }
  private static List<IElement> creator() {
    return new ArrayList<>();
  }
  private static Point2D newLocation(Point2D oldPoint, Point2D oldScreenSize) {
    double ratioW = 1 / (oldScreenSize.getX() / screenSize.getX());
    double ratioH = 1 / (oldScreenSize.getY() / screenSize.getY());
    return new Ponto(ratioW * oldPoint.getX(), ratioH * oldPoint.getY());
  }
}
