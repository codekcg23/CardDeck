package cardgame;

import controller.GameController;
import models.Deck;
import view.View;

public class CardGame {
    public  static  void  main(String[] args){
        GameController gc = new GameController(new View(),new Deck());
        gc.run();

    }
}
