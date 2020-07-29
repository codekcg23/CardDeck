package game;
import java.util.List;
import models.Player;
import models.PlayingCard;

public class GameEvaluator {
    public Player evaluateWinner(List<Player> players) {
        Player bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;

        for (Player player : players) {
            boolean newsBestPlayer = false;
            if (bestPlayer == null) {
                newsBestPlayer = true;
            } else {
                PlayingCard pc = player.getCard(0);
                int thisRank = pc.getRank().vaalue();
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
                bestRank = pc.getRank().vaalue();
                bestSuit = pc.getSuit().value();
            }

        }
        ;
        return bestPlayer;
    }
}