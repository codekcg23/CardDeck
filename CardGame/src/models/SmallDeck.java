package models;

import java.util.ArrayList;

public class SmallDeck extends  Deck{
    public SmallDeck(){
        cards = new ArrayList<PlayingCard>();
        for(Rank rank:Rank.values()){
            for(Suit suit :Suit.values()){
                if(rank.value() >=7) {
                    System.out.println("Creating card:{" + rank + "]");
                    cards.add(new PlayingCard(rank, suit));
                }
            }
        }
        shuffle();
    }
}
