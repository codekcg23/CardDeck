package controller;

import models.*;
import game.GameEvaluator;
import view.GameViewable;
import view.GameViewables;

import java.util.ArrayList;


public class GameController {

    enum GameState {
        AddingPlayers,
        CardDealt,
        WinnerRevealed,
        AddingView
    }

    Deck deck;
    ArrayList<IPlayer> players;
    IPlayer winner;
    GameViewables views;
    GameState gameState;
    GameEvaluator gameEvaluator;

    public GameController(GameViewable view, Deck deck, GameEvaluator gameEvaluator){

    //    this.view = view;
        views = new GameViewables();
        this.deck = deck;
        players = new ArrayList<IPlayer>();
        gameState = GameState.AddingPlayers;
        view.setController(this);
        this.gameEvaluator = gameEvaluator;  // pass in the correct specific implementation as that parameter. low or high
        addViewable(view);
    }

    public  void  addViewable(GameViewable newView){
        GameState currentState = gameState;
        gameState = GameState.AddingView;
        newView.setController(this);
        views.addViewables(newView);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        gameState = currentState;
    }

    public void run(){
        while (true){
            switch (gameState){
                case AddingPlayers:
                    views.promptForPlayerName();
                    break;
                case CardDealt:
                    views.promptForFlip();
                    break;
                case WinnerRevealed:
                    views.promptForNewGame();
                    break;
                case AddingView:
                    break;
            }
        }
    }


    public void addPlayer(String playerName) {
        if(gameState == GameState.AddingPlayers){
            players.add(new Player(playerName));
            views.showPlayerName(players.size(),playerName);
        }
    }
    public void startGame() {
        if(gameState != GameState.CardDealt){
            deck.shuffle();
            int playerIndex = 1;
            for(IPlayer player: players){
                player.addCardToHand(deck.removeTopCard());
                views.showFaceDownCardForPlayer(playerIndex++,player.getName());
            }
            gameState = GameState.CardDealt;
        }
    }
    public void flipCards() {
        int playerIndex = 1;
        for(IPlayer player: players){
            PlayingCard pc = player.getCard(0);
            pc.flip();
            views.showCardForPlayer(playerIndex++,player.getName(),pc.getRank().toString(),pc.getSuit().toString());
        }
        gameState = GameState.WinnerRevealed;
        evaluateWinner();
        displayWinner();
        rebuildDeck();

    }
    public void restartGame(){
        rebuildDeck();
        gameState = GameState.AddingPlayers;

    }

    void evaluateWinner() {

        //  winner = gameEvaluator.evaluateWinner(players);      before decorater design pattern
        winner = new WinnerPlayer(gameEvaluator.evaluateWinner(players));

    }
    void displayWinner() {

        views.showWinner(winner.getName()); // decorated getName()
    }
    void rebuildDeck() {
        for(IPlayer player:players){
            deck.returnCardToDeck(player.removeCard());
        }

    }



}
