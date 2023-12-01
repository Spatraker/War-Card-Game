package wargame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                if(rank != Card.Rank.JOKER) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        // Adding 2 jokers
        cards.add(new Card(null, Card.Rank.JOKER));
        cards.add(new Card(null, Card.Rank.JOKER));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.remove(cards.size() - 1);
    }
}
