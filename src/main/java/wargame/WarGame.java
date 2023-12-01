package wargame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarGame {
    private final Player player1 = new Player("You");
    private final Player player2 = new Player("Computer");

    public void playGame() {
        Deck deck = new Deck();
        deck.shuffle();

        distributeCards(deck);

        Scanner scanner = new Scanner(System.in);
        while (player1.hasCards() && player2.hasCards()) {
            System.out.println("Press Enter to play a round.");
            scanner.nextLine();

            Card player1Card = player1.playCard();
            Card player2Card = player2.playCard();

            System.out.println(player1 + " play " + player1Card);
            System.out.println(player2 + " plays " + player2Card);

            int comparison = player1Card.compareTo(player2Card);

            if (comparison > 0) {
                player1.receiveCard(player1Card);
                player1.receiveCard(player2Card);
                System.out.println(player1 + " wins this round!");
            } else if (comparison < 0) {
                player2.receiveCard(player1Card);
                player2.receiveCard(player2Card);
                System.out.println(player2 + " wins this round!");
            } else {
                System.out.println("It's a tie! This is WAR!");
                Player winner = conductWar(player1, player2);
                if (winner != null) {
                    System.out.println(winner + " wins the war!");
                } else {
                    System.out.println("The war is a tie!");
                }
            }
        }

        if (!player1.hasCards()) {
            System.out.println(player2 + " wins the game!");
        } else {
            System.out.println(player1 + " wins the game!");
        }
    }

    private void distributeCards(Deck deck) {
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) player1.receiveCard(deck.deal());
            else player2.receiveCard(deck.deal());
        }
    }

    private Player conductWar(Player player1, Player player2) {
        if (player1.cardsLeft() < 4 || player2.cardsLeft() < 4) {
            return player1.cardsLeft() > player2.cardsLeft() ? player1 : player2;
        }

        List<Card> warPile = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            warPile.add(player1.playCard());
            warPile.add(player2.playCard());
        }

        Card player1WarCard = player1.playCard();
        Card player2WarCard = player2.playCard();

        System.out.println(player1 + " plays war card " + player1WarCard);
        System.out.println(player2 + " plays war card " + player2WarCard);

        int comparison = player1WarCard.compareTo(player2WarCard);
        if (comparison > 0) {
            player1.receiveCards(warPile);
            player1.receiveCard(player1WarCard);
            player1.receiveCard(player2WarCard);
            return player1;
        } else if (comparison < 0) {
            player2.receiveCards(warPile);
            player2.receiveCard(player1WarCard);
            player2.receiveCard(player2WarCard);
            return player2;
        } else {
            return null; // Another tie
        }
    }

    public static void main(String[] args) {
        WarGame game = new WarGame();
        Scanner scanner = new Scanner(System.in);

        String response;
        do {
            game.playGame();
            System.out.println("Do you want to play again? (yes or no)");
            response = scanner.nextLine().trim().toLowerCase();
        } while (response.equals("yes"));

        System.out.println("Thanks for playing!");
    }
}
