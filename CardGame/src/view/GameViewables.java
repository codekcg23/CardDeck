package view;

import controller.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GameViewables implements GameViewable{

    //=============using composite design pattern================
   // List<GameViewable> views;  //list thread-safe doesn't support
    Vector<GameViewable> views;

    public  GameViewables(){
        views = new Vector<>();
    }

    public void addViewables(GameViewable view){
        views.add(view);

    }
    @Override
    public void setController(GameController controller) {
        for (GameViewable view : views) {
            view.setController(controller);
        }
    }

    @Override
    public void promptForPlayerName() {
        for (GameViewable view : views) {
            view.promptForPlayerName();
        }
    }

    @Override
    public void promptForFlip() {
        for (GameViewable view : views) {
            view.promptForFlip();
        }

    }

    @Override
    public void promptForNewGame() {
        for (GameViewable view : views) {
            view.promptForNewGame();
        }

    }

    @Override
    public void showPlayerName(int playerIndex, String name) {
        for (GameViewable view : views) {
            view.showPlayerName(playerIndex, name);
        }

    }

    @Override
    public void showFaceDownCardForPlayer(int playerIndex, String name) {
        for (GameViewable view : views) {
            view.showFaceDownCardForPlayer(playerIndex, name);
        }

    }

    @Override
    public void showCardForPlayer(int playerIndex, String Name, String rank, String suit) {
        for (GameViewable view : views) {
            view.showCardForPlayer(playerIndex, Name,rank,suit);
        }

    }

    @Override
    public void showWinner(String winnerName) {
        for (GameViewable view : views) {
            view.showWinner(winnerName);
        }
    }
}
