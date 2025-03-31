import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    //restart the game after three losses in a row, taking parameters of linked lists from the main class (computer cards, player cards, and the deck itself)
    public static void rage_quit(LinkedList player1,LinkedList computer){
        LinkedList deck = initialize_deck(); //reinitialize the deck
        deck.shuffle(512); //reshuffle the deck

        //reestablish the player and computer hands
        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("Starting Blind mans Bluff \n");
        //initialize and declare statistic values
        int rounds = 0;
        int playerWins = 0;
        int playerLosses = 0;
        int loseStreak = 0;
        String target;

        //play five rounds (unless you rage quit)
        while (rounds < 5 ){
            //declare the cards being used for the round
            Card computerCard = computer.remove_from_head();
            Card playerCard = player1.remove_from_head();

            //player card is higher than the opponent's
            if (playerCard.compareTo(computerCard) == 1){
                target = "H";
            }

            //player card is lower than the opponent's
            else{
                target = "L";
            }

            //let the user see the opponent's card and allow a response to be recorded (H or L) depending on what they think about their card's value
            System.out.print("This is the Computer's Card: ");
            computerCard.print_card();
            System.out.println();
            System.out.print("Do you think your card is higher or lower than the computer's? (\"H\" or \"L\"): ");
            Scanner kb = new Scanner(System.in);
            String response = kb.next();

            //player guesses correctly
            if (response.equals(target)){
                playerWins++;
                System.out.println("Win!");
            }

            //player guesses incorrectly
            else{
                playerLosses++;
                loseStreak++; //adds to the losing streak
                System.out.println("Loss :(");
            }

            //show the player's card
            System.out.print("This was your card: ");
            playerCard.print_card();
            System.out.println();
            System.out.println();
            rounds++; //iterate through the rounds to be able to exit the while loop/end the game

            //checks if the losing streak was met (3) to run a rage quit method and restart the game
            if(loseStreak == 3){
                rage_quit(player1,computer);

                //reset the statistics
                playerWins = 0;
                playerLosses = 0;
                rounds = 0;
                loseStreak = 0;
                System.out.println("You just \"rage quit\"");
            }
        }

        //display the win/loss statistics
        System.out.println("Wins: " + playerWins);
        System.out.print("Losses: " + playerLosses);
    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        //deck.print();
        //deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        //deck.print();
        //deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}
