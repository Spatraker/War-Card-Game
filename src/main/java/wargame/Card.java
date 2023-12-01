package wargame;

public class Card implements Comparable<Card> {
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(11), QUEEN(12), KING(13), ACE(14), JOKER(15);

        private final int cardValue;

        Rank(int value) {
            this.cardValue = value;
        }

        public int getValue() {
            return cardValue;
        }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.rank.getValue(), otherCard.rank.getValue());
    }

    @Override
    public String toString() {
        if (rank == Rank.JOKER) {
            return "JOKER 🂿"; // Joker card symbol
        }

        String unicodeCardSymbol;
        int baseValue;
        switch (suit) {
            case SPADES:
                baseValue = 0x1F0A0;
                break;
            case HEARTS:
                baseValue = 0x1F0B0;
                break;
            case DIAMONDS:
                baseValue = 0x1F0C0;
                break;
            case CLUBS:
                baseValue = 0x1F0D0;
                break;
            default:
                throw new IllegalStateException("Unexpected suit: " + suit);
        }
        unicodeCardSymbol = new String(Character.toChars(baseValue + rank.getValue()));

        return rank.name() + " of " + suit.name() + " " + unicodeCardSymbol;
    }

    /*
    public static void main(String[] args) {
        System.out.println(new Card(Suit.SPADES, Rank.TWO));
        System.out.println(new Card(Suit.HEARTS, Rank.SEVEN));
    }
     */
}
