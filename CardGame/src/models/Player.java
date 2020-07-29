package models;

public class Player {
    private String name;
    Hand hand;

    public  Player (String name){
        this.name= name;
        hand = new Hand();
    }
    public String getName(){
        return name;
    }

    public void addCardToHand(PlayingCard pc){
        hand.addCard(pc);
    }

    public  PlayingCard getCard(int index){
        return hand.getCard(index);
    }

    public PlayingCard removeCard(){
        return hand.removeCard();
    }

}
