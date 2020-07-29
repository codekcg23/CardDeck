package controller;

import models.Deck;
import models.Player;
import models.PlayingCard;
import view.View;
import game.GameEvaluator;

import java.util.ArrayList;


public class GameController {

    enum GameState {
        AddingPlayers,
        CardDealt,
        WinnerRevealed
    }

    Deck deck;
    ArrayList<Player> players;
    Player winner;
    View view;
    GameState gameState;

    public GameController(View view,Deck deck){

        this.view = view;
        this.deck = deck;
        players = new ArrayList<>();
        gameState = GameState.AddingPlayers;
        view.setController(this);

    }

    public void run(){
        while (true){
            switch (gameState){
                case AddingPlayers:
                    view.promptForPlayerName();
                    break;
                case CardDealt:
                    view.promptForFlip();
                    break;
                case WinnerRevealed:
                    view.promptForNewGame();
                    break;
            }
        }
    }


    public void addPlayer(String playerName) {
        if(gameState == GameState.AddingPlayers){
            players.add(new Player(playerName));
            view.showPlayerName(players.size(),playerName);
        }
    }
    public void startGame() {
        if(gameState != GameState.CardDealt){
            deck.shuffle();
            int playerIndex = 1;
            for(Player player: players){
                player.addCardToHand(deck.removeTopCard());
                view.showFaceDownCardForPlayer(playerIndex++,player.getName());
            }
            gameState = GameState.CardDealt;
        }
    }
    public void flipCards() {
        int playerIndex = 1;
        for(Player player: players){
            PlayingCard pc = player.getCard(0);
            pc.flip();
            view.showCardForPlayer(playerIndex++,player.getName(),pc.getRank().toString(),pc.getSuit().toString());
        }
        gameState = GameState.WinnerRevealed;
        evaluateWinner();
        displayWinner();
        rebuildDeck();

    }

    void evaluateWinner() {

        winner = new GameEvaluator().evaluateWinner(players);

    }
    void displayWinner() {

        view.showWinner(winner.getName());
    }
    void rebuildDeck() {
        for(Player player:players){
            deck.returnCardToDeck(player.removeCard());
        }

    }



}
