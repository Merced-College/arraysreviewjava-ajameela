/*
Jameela Ahmed
Trinity Hurtado
Rito Murillo

Date 1/27
Time: 2 PM

*/

import java.util.Random; // generates random numbers - Trinity H.
import java.util.Scanner; // this is a java utility scanner to allow input -Rito M.
public class BlackJack {

    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" }; // Its a fixed array that stores the 4 suits for the deck - Trinity H.
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace" };  
    private static final int[] DECK = new int[52]; // initializing 52 elements to the array 'DECK' - Jameela A
    private static int currentCardIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wins = 0;
        int losses = 0;
        int ties = 0;

        String playAgain;

        do {
        // Reset deck for each round
        currentCardIndex = 0;
        initializeDeck();/* Rito Murillo. This initializes the deck */
        shuffleDeck(); /* Trinity Hurtado. This intialzies the shuffle */

        int playerTotal = dealInitialPlayerCards(); /* Rito Murillo. This assigns the player total to the inital player cards */
        int dealerTotal = dealInitialDealerCards(); /* Trinity Hurtado. This assigns the dealer total to the dealer intital cards */

        playerTotal = playerTurn(scanner, playerTotal);
        if (playerTotal > 21) {
        System.out.println("You busted! Dealer wins."); /* Rito Murillo. if the players total past 21 this linhe print outs that player lost. */
        losses++;
        } else {
        dealerTotal = dealerTurn(dealerTotal);
        int result = determineWinner(playerTotal, dealerTotal);

        if (result == 1) {
         wins++;
        } else if (result == -1) {
        losses++;
        } else {
        ties++;
        }
        }

        System.out.println("\n--- Scoreboard ---");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Ties: " + ties);

        System.out.print("\nPlay another round? (yes/no): ");
        playAgain = scanner.nextLine().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void initializeDeck() {
        for (int i = 0; i < DECK.length; i++) { // This initializes the deck from 0 is to be able to start from 0 until the deck is over - Rito M.
            DECK[i] = i; 
        }
    }

    private static void shuffleDeck() {
        Random random = new Random(); // utilizes the Random utility from import to generate a random card -  Jameela A
        for (int i = 0; i < DECK.length; i++) {
            int index = random.nextInt(DECK.length);
            int temp = DECK[i];
            DECK[i] = DECK[index];
            DECK[index] = temp;
        }
        System.out.println("printed deck");
        for (int i = 0; i < DECK.length; i++) {
            System.out.println(DECK[i] + " "); // Shuffles the cards and then prints them out. -Trinity H.
        }
    }

        // Deals cards to players - Jameela A
    private static int dealInitialPlayerCards() { 
        int card1 = dealCard();
        int card2 = dealCard();
        System.out.println("Your cards: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4] + " and "
                + RANKS[card2] + " of " + SUITS[card2 / 13]);
        return cardValue(card1) + cardValue(card2);  // Returns the sum of card1 and card2 - Rito M.
    }

    // Deals the dealer their cards - Trinity H
    private static int dealInitialDealerCards() {
        int card1 = dealCard();
        System.out.println("Dealer's card: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4]); 
        return cardValue(card1); // This part of the code deals one card, it prints out the dealers card with its rank and suit from the current cardIndex and returns value of that one card - Rito M.
    }
    
    // Take in input from user whether they want hit or stand - Jameela A
    private static int playerTurn(Scanner scanner, int playerTotal) {
        while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
            String action = scanner.nextLine().toLowerCase(); // this takes in input from the user and makes it all lowercase - Jameela A.
            if (action.equals("hit")) {
                int newCard = dealCard(); // If player hits, dealer deals new card - Rito M.
                playerTotal += cardValue(newCard);  // New sum of the cards dealt - Trinity H.
                System.out.println("new card index is " + newCard); // this prints out a new line with the new card index + the new card - Rito M. 
                System.out.println("You drew a " + RANKS[newCard] + " of " + SUITS[DECK[currentCardIndex] % 4]); 
                if (playerTotal > 21) {
                    break;
                }
            } else if (action.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'."); // if the player inputs something other than hit or stand, it output this string. - Rito M
            }
        }
        return playerTotal;
    }

 
    private static int dealerTurn(int dealerTotal) {
        while (dealerTotal < 17) {
            int newCard = dealCard(); // assigned a random card to the 'newCard' - Jameela A.
            dealerTotal += cardValue(newCard); 
        }
        System.out.println("Dealer's total is " + dealerTotal); 
        return dealerTotal; // if the dealer total is less than 17 deal a new card than that equal to a new total  - Trinitiy H.
    }
    
    // This determines the winner - Jameela A
    private static int determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) {  // If the dealer loses, it print "you win" - Trinity H
            System.out.println("You win!"); 
            return 1;
        } else if (dealerTotal == playerTotal) { // If dealer and player have the same amount, it prints "its a tie!" - Rito M.
            System.out.println("It's a tie!");
            return 0;
        } else {
            System.out.println("Dealer wins!"); // Player loses - Jameela A
            return -1;
        } 
    } 


    private static int dealCard() {
        return DECK[currentCardIndex++] % 13; // Deals random card - Trinity H.
    }

    // if the card is less than 9 + 2 we have to return 10 because it is the highest value - Jameela A
    private static int cardValue(int card) { 
        return card < 9 ? card + 2 : 10; 
    }

    int linearSearch(int[] numbers, int key) {
        int i = 0;
        for (i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1; // not found
    }
}
