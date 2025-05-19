import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES
}

enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        switch (rank) {
            case ACE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                return 0; // Shouldn't reach here
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty())
            return null;
        return cards.remove(0);
    }
}

class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int value = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            value += card.getValue();
            if (card.getValue() == 1) {
                hasAce = true;
            }
        }
        if (hasAce && value + 10 <= 21) {
            value += 10; // Treat Ace as 11
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
}

class Player {
    private final String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    public int getHandValue() {
        return hand.getValue();
    }

    public void clearHand() {
        hand = new Hand();
    }

    @Override
    public String toString() {
        return name + "'s Hand:\n" + hand;
    }
}

class Dealer extends Player {
    public Dealer() {
        super("Dealer");
    }

    public void revealFirstCard() {
        System.out.println("Dealer's Hand:");
        System.out.println("Face-down card");
        System.out.println(getHand().get(1)); // Second card
    }
}

public class BlackjackGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player = new Player("Player");
        Dealer dealer = new Dealer();

        // Deal initial cards
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());

        System.out.println(player);
        System.out.println("Player's hand value: " + player.getHandValue());
        dealer.revealFirstCard();

        // Game logic can be implemented here
    }
}
