package models;

public class PlayingCard {
     Rank rank;
     Suit suit;
    boolean faceUp;  // card show or not

    public PlayingCard(Rank r, Suit s){
        rank = r;
        suit = s;
        faceUp = false;
    }

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit(){
        return suit;
    }
    public boolean flip(){
        faceUp = !faceUp;
        return faceUp;
    }
}
