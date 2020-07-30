package game;
import java.util.List;
import models.Player;
import models.PlayingCard;

public interface GameEvaluator {
     Player evaluateWinner(List<Player> players) ;  // default public and abstract
}