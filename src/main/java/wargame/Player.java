package wargame;

import java.util.List;
import java.util.Collection;
import java.util.Stack;

public class Player {
    private final String name;
    private final Stack<Card> deck = new Stack<>();

    public Player(String name) {
        this.name = name;
    }

    public void receiveCard(Card card) {
        deck.push(card);
    }

    public void receiveCards(List<Card> cards) {
        this.deck.addAll(cards);
    }


    public int cardsLeft() {
        return deck.size();
    }

    public Card playCard() {
        return deck.pop();
    }

    public boolean hasCards() {
        return !deck.isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }
}
