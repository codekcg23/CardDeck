package game;
import java.util.List;
import models.IPlayer;

public interface GameEvaluator {
     IPlayer evaluateWinner(List<IPlayer> players) ;  // default public and abstract
}