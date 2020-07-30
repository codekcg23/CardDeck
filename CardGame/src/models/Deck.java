package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public abstract class Deck {

    ArrayList<PlayingCard> cards;


    public  void  shuffle(){
        Random random = new Random();
        for(int i=0; i<cards.size(); ++i){
            Collections.swap(cards,i,random.nextInt(cards.size()));
        }
    }
    public  PlayingCard removeTopCard(){
        return cards.remove(0);
    }

    public  void  returnCardToDeck(PlayingCard pc){
        cards.add(pc);
    }




}
