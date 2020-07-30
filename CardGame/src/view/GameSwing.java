package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class GameSwing implements GameViewable {
    GameController controller;

    JButton btnAddPlayer;
    JButton btnDealCards;
    JButton btnFindWinner;
    JTextArea textArea;
    static char nextPlayerName = 'A';  // shared

    public void createAndShowGUI() {
        // create the main display area, with enough initial space
        JFrame frame = new JFrame("MVC-SOLID-Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // all of the controls will appear vertically
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        addAddPlayerButton(contentPane);
        addDealCardsButton(contentPane);
        addFindWinnerButton(contentPane);
        addNewWindow(contentPane);

        addControllerCommandTracker(contentPane);

        frame.setVisible(true);
    }

    // when clicked, tell controller to add a player of the given name
    // this SHOULD bring up a dialog box to allow the user to enter a name
    // but that is beyond the scope of what we need to learn
    // for simplicity, we'll just tell it to add "IPlayer A", "IPlayer B", etc.
    private void addAddPlayerButton (Container contentPane) {
        btnAddPlayer = new JButton("Add A IPlayer");
        addCenteredComponent (btnAddPlayer, contentPane);
        btnAddPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Current players:\n");
                controller.addPlayer("IPlayer " + nextPlayerName++);
            }
        });
    }

    // when clicked, tell controller to deal cards to players
    private void addDealCardsButton (Container contentPane) {
        btnDealCards = new JButton("Deal Cards");
        addCenteredComponent(btnDealCards, contentPane);
        btnDealCards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Cards dealt:\n");
                controller.startGame();
            }
        });
    }

    // when clicked, tell controller to flip cards and determine who won
    private void addFindWinnerButton(Container contentPane) {
        btnFindWinner = new JButton("Find Winner");
        addCenteredComponent(btnFindWinner, contentPane);
        btnFindWinner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Show your cards:\n");
                controller.flipCards();
            }
        });
    }

    // a simple place to display what the controller is telling us
    // very similar to our command line version
    private void addControllerCommandTracker(Container contentPane) {
        textArea = new JTextArea("Game Status\n", 100, 1);
        JScrollPane scrollPane = new JScrollPane(textArea);
        addCenteredComponent(scrollPane, contentPane);
        textArea.setSize(500, 500);
    }

    // make sure that every time something is added to the text area,
    // scroll down to the bottom so that it is visible
    // textArea control does not have an auto-scroll option
    // so we have to do it ourselves
    private void appendText (String text) {
        textArea.append(text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // add new windows
    private void addNewWindow(Container contentPane){
        JButton btnAddWindow = new JButton("Add Window");
        addCenteredComponent (btnAddWindow, contentPane);
        btnAddWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameSwing game = new GameSwing();
                game.createAndShowGUI();
                controller.addViewable(game);
            }
        });
    }


    // all controls are added so they are centered horizontally in the area
    private void addCenteredComponent(JComponent component, Container contentPane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(component);
    }

    // GameViewable interface implementations
    public void setController(GameController controller) {
        this.controller = controller;
    }

    // same implementation as our CommandLineView
    // just show what the controller is telling us
    @Override
    public void showPlayerName(int playerIndex, String playerName) {
        appendText("[" + playerIndex + "][" + playerName +"]");
    }

    @Override
    public void showCardForPlayer(int playerIndex, String playerName, String cardRank, String cardSuit) {
        appendText("[" + playerName + "][" + cardRank + ":" + cardSuit + "]");
    }

    @Override
    public void showWinner(String winnerName) {
        appendText("Winner!\n" + winnerName);
    }

    @Override
    public void showFaceDownCardForPlayer(int playerIndex, String name) {
        appendText("[" + name + "][][]");
    }


    @Override
    public void promptForPlayerName() {
      //  System.out.println("Enter IPlayer Name:");
        // it's ok to add names
        btnAddPlayer.setEnabled(true);

        // it's ok to start, one a player has been added
        btnDealCards.setEnabled(nextPlayerName > 'A');
        System.out.println(nextPlayerName);
        System.out.println(nextPlayerName > 'A');


        // but not to ask to find a winner
        btnFindWinner.setEnabled(false);
    }

    @Override
    public void promptForFlip() {
      //  System.out.println("Press enter to reveal cards");

        // past the time to add names
        btnAddPlayer.setEnabled(false);

        // or deal cards
        btnDealCards.setEnabled(false);

        // but you can ask to find a winner
        btnFindWinner.setEnabled(true);
    }

    @Override
    public void promptForNewGame() {
     //   System.out.println("Press enter to deal again");

        // past the time to add names
        btnAddPlayer.setEnabled(false);

        // ok to deal cards for the next game
        btnDealCards.setEnabled(true);

        // can't find a winner yet
        btnFindWinner.setEnabled(false);
    }

}
