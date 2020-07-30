package view;

import controller.GameController;

public interface GameViewable {

     void  setController(GameController controller);
     void promptForPlayerName();
     void  promptForFlip();
     void  promptForNewGame();
     void showPlayerName(int playerIndex, String name);
     void showFaceDownCardForPlayer(int playerIndex, String name);
     void showCardForPlayer(int playerIndex, String Name, String rank, String suit);
     void showWinner (String winnerName);

}
