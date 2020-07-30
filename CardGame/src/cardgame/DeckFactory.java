package cardgame;

import models.Deck;
import models.NormalDeck;
import models.SmallDeck;
import models.TestDeck;

public class DeckFactory {
// ============= using factory pattern
    public  enum DeckType{
        Normal,
        Small,
        Test
    };

    public static Deck makeDeck(DeckType type) {
        switch (type) {
            case Normal: return new NormalDeck();
            case Small: return new SmallDeck();
            case Test: return new TestDeck();
        }

        // fallback
        return new NormalDeck();
    }
}
// our object create by this class