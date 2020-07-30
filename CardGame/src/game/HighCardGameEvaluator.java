package game;

import models.IPlayer;
import models.PlayingCard;

import java.util.List;

public class HighCardGameEvaluator implements GameEvaluator {

    public IPlayer evaluateWinner(List<IPlayer> players) {
        IPlayer bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;

        for (IPlayer player : players) {
            boolean newsBestPlayer = false;
            if (bestPlayer == null) {
                newsBestPlayer = true;
            } else {
                PlayingCard pc = player.getCard(0);
                int thisRank = pc.getRank().value();
                if (thisRank > bestRank) {
                    newsBestPlayer = true;
                } else {
                    if (pc.getSuit().value() > bestSuit) {
                        newsBestPlayer = true;
                    }
                }

            }
            if (newsBestPlayer) {
                bestPlayer = player;
                PlayingCard pc = player.getCard(0);
                bestRank = pc.getRank().value();
                bestSuit = pc.getSuit().value();
            }

        }
        ;
        return bestPlayer;
    }


}
