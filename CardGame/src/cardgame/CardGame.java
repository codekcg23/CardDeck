package cardgame;

import controller.GameController;
import game.HighCardGameEvaluator;
import game.LowCardGameEvaluator;
import models.Deck;
import view.CmdView;
import view.GameSwing;



public class CardGame {
    public  static  void  main(String[] args){

        // swing GUI
        GameSwing gs = new GameSwing();
        gs.createAndShowGUI();

        // cmd GUI
        CmdView cv = new CmdView();

        // JavaFX GUI

        // Highest card player winner
        HighCardGameEvaluator he = new HighCardGameEvaluator();

        // Lowest card player winner
        LowCardGameEvaluator le = new LowCardGameEvaluator();

        GameController gc = new GameController(gs,new Deck(),he);
        gc.run();

    }
}
