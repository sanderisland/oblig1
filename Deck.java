package oblig1;


import java.util.*;
import java.util.stream.Collectors;

/**
 * A deck of cards. Contains methods for ditt & datt.
 *
 * a) Lag en klasse Deck som oppretter en fullstendig kort-stokk (52 kort)
 * b) Lag en metode «assign» i Deck som plukker tilfeldig n kort fra kortstokken og returnerer disse i en Collection.
 *     «n» er et tall mellom 1 og 52 som sendes inn som parameter til assign funksjonen.
 * c) Skriv et uttrykk med filter og forEach som skriver ut alle spar-kort (suit = 'S').
 * d) Skriv et uttrykk med filter og collect som samler alle hjerter-kort (suit = 'H') i en ny liste.
 * e) Skriv et uttrykk med map som gir en ny liste med kortenes kortfarge.
 * f) Skriv et uttrykk med reduce som gir summen av kortverdiene (face).
 * g) Skriv et uttrykk med anyMatch som sier om spar dame finnes i lista.
 * h) Skriv et uttrykk som sier om lista er en poker-flush, dvs. har fem kort hvor alle har samme kortfarge.
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> chosenCards = new ArrayList<>();

    /**
     * Initializes the methods in the class.
     */
    public Deck()
    {
        createDeck();
        shuffleDeck(5);
        printSpades();
        collectHearts();
        printHearts();
        turnIntoColor(chosenCards);
        printColors();
        getValue(chosenCards);
        printValue();
        printQueenSpadesExist();
        printPokerFlush();
    }

    /**
     * a) Lag en klasse Deck som oppretter en fullstendig kort-stokk (52 kort)
     *
     * Creates a deck of cards.
     */
    public void createDeck()
    {
        for(int index =1; index <= 13; index++)
        {
            deck.add(new Card("Spades", index));
            deck.add(new Card("Hearts", index));
            deck.add(new Card("Diamonds", index));
            deck.add(new Card("Clubs", index));
        }
    }

    /**
     * Printing the full deck of cards.
     */
    public void printDeck()
    {
        System.out.println("Total cards: " + deck.size());
        for(Card card: deck)
        {
            card.getDetails();
        }
    }

    /**
     * Shuffles the deck and picks a decided amount of random cards for you to have on your hand.
     *
     * @param amount How many cards you should have on your hand.
     * @return chosenCards The cards you have on your hand.
     */
    public Collection<Card> shuffleDeck(int amount)
    {
        // Shuffle the deck of cards
        Collections.shuffle(deck);
        // Go through the n first cards
        for(int index = 0; index < amount; index ++)
        {
            // Add the card into the ArrayList of the response
            chosenCards.add(deck.get(index));
        }
        System.out.println("Your " + amount + " cards:");
        for(Card tempCards_FullDeck: chosenCards)
        {
            tempCards_FullDeck.getDetails();
        }
        return chosenCards;
    }

    /**
     * c) Skriv et uttrykk med filter og forEach som skriver ut alle spar-kort (suit = 'S').
     * Filters all the spades on your hand.
     */
    public void printSpades()
    {
        ArrayList<Card> spades = new ArrayList<>();
        chosenCards.stream().filter(card -> card.getSuit().equals("Spades")).forEach(spades::add);
        System.out.println("Spades:");
        for (Card tempSpades: spades)
        {
            tempSpades.getDetails();
        }
    }

    /**
     * d) Skriv et uttrykk med filter og collect som samler alle hjerter-kort (suit = 'H') i en ny liste.
     * Filters all the hearts on your hand.
     */
    public ArrayList<Card> collectHearts()
    {
        ArrayList<Card> hearts = new ArrayList<>();
        chosenCards.stream().filter(card -> card.getSuit().equals("Hearts")).forEach(hearts::add);
        return hearts;
    }

    /**
     * Printing the hearts on your hand.
     */
    public void printHearts()
    {
        System.out.println("Hearts:");
        List<Card> collectedCards = collectHearts();
        for (Card tempHearts: collectedCards)
        {
            tempHearts.getDetails();
        }
    }

    /**
     * e) Skriv et uttrykk med map som gir en ny liste med kortenes kortfarge.
     * Says if your cards are red or black.
     */
    public  List<String> turnIntoColor(ArrayList<Card> cards) {
        return chosenCards.stream().map(card ->
                (card.getSuit().equals("Hearts") || card.getSuit().equals("Diamonds")) ? "RED" : "BLACK")
                .collect(Collectors.toList());
    }

    /**
     * Printing a list of your cards, but it only shows what color they are (red or black).
     */
    public void printColors()
    {
        System.out.print("Print colors: ");
        System.out.println(turnIntoColor(chosenCards));
    }

    /**
     * f) Skriv et uttrykk med reduce som gir summen av kortverdiene (face).
     * Adding all the card's faces and returns a sum.
     */
    public int getValue(ArrayList<Card> cards) {
        return chosenCards.stream().reduce(0, (subtotal, card) -> subtotal + card.getFace(), Integer::sum);
    }

    /**
     * Printing the sum of your cards.
     */
    public void printValue()
    {
        System.out.print("The value of your cards: ");
        System.out.println(getValue(chosenCards));
    }

    /**
     * g) Skriv et uttrykk med anyMatch som sier om spar dame finnes i lista.
     * Checks if the queen of spades is on your hand.
     */
    public boolean queenSpadesExist()
    {
        return chosenCards.stream().anyMatch(card -> card.getSuit().equals("Spades") && card.getFace() == 12);
    }

    /**
     * Prints a message showing if the queen of spades is on your hand or not.
     */
    public void printQueenSpadesExist()
    {
        if(queenSpadesExist() == true)
        {
            System.out.println("The queen of spades is on your hand.");
        }
        else
        {
            System.out.println("You don't have the queen of spades.");
        }
    }

    /**
     * h) Skriv et uttrykk som sier om lista er en poker-flush, dvs. har fem kort hvor alle har samme kortfarge.
     * Checks if you have a flush or not.
     */
    public boolean pokerFlush()
    {
        ArrayList<Card> red = new ArrayList<>();
        ArrayList<Card> black = new ArrayList<>();
        boolean pokerFlushExist;
        chosenCards.stream().filter(card -> card.getSuit().equals("Spades") || card.getSuit().equals("Clubs")).forEach(
                black::add);
        chosenCards.stream().filter(card -> card.getSuit().equals("Hearts") || card.getSuit().equals("Diamonds")).forEach(
                red::add);
        if(red.size() >= 5 || black.size() >= 5)
        {
            pokerFlushExist = true;
        }
        else
        {
            pokerFlushExist = false;
        }
        return pokerFlushExist;
    }

    /**
     * Prints a message that shows if you have a flush or not.
     */
    public void printPokerFlush()
    {
        if(pokerFlush() == true)
        {
            System.out.println("You have a flush!");
        }
        else
        {
            System.out.println("No flush this time...");
        }
    }
}






