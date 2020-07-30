package models;

public class WinnerPlayer implements IPlayer {
    //============= decorator design pattern ============
    IPlayer winner;
    public  WinnerPlayer(IPlayer player){
        winner = player;
    }
    @Override
    public String getName() {
        return "******** " + winner.getName() + " *********";
    }

    @Override
    public void addCardToHand(PlayingCard pc) {

    }

    @Override
    public PlayingCard getCard(int index) {
        return null;
    }

    @Override
    public PlayingCard removeCard() {
        return null;
    }
}
