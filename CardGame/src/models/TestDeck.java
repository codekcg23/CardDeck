package models;

import java.util.ArrayList;

public class TestDeck extends  Deck{
    public TestDeck(){
        cards = new ArrayList<PlayingCard>();
        for(Rank rank:Rank.values()){
            for(Suit suit :Suit.values()){
                System.out.println("Creating card:{" + rank + "]");
                cards.add(new PlayingCard(rank,suit));
            }
        }
        shuffle();
    }
}
