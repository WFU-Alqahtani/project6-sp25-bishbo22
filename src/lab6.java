import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    public static void rage_quit(LinkedList deck,LinkedList player1,LinkedList computer){
        deck = initialize_deck();
        deck.shuffle(512);
        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");
        int rounds = 0;
        int playerWins = 0;
        int playerLosses = 0;
        int loseStreak = 0;
        String target = null;

        while (rounds < 5 ){
            Card computerCard = computer.remove_from_head();
            Card playerCard = player1.remove_from_head();
            //player card is higher
            if (playerCard.compareTo(computerCard) == 1){
                target = "H";
            }
            //player card is lower
            else{
                target = "L";
            }
            System.out.print("This is the Computer's Card: ");
            computerCard.print_card();
            System.out.println();
            System.out.print("Do you think your card is higher or lower than the computer's? (\"H\" or \"L\"): ");
            Scanner kb = new Scanner(System.in);
            String response = kb.next();
            if (response.equals(target)){
                playerWins++;
                loseStreak = 0;
                System.out.println("Win!");
            }
            else{
                playerLosses++;
                loseStreak++;
                System.out.println("Loss :(");
            }
            System.out.print("This was your card: ");
            playerCard.print_card();
            System.out.println();
            System.out.println();
            rounds++;
            if(loseStreak == 3){
                rage_quit(deck,player1,computer);
                rounds = 0;
                System.out.println("You just \"rage quit\"");
            }
        }
        System.out.println("Wins: " + playerWins);
        System.out.println("Losses: " + playerLosses);

    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        //deck.print();
        deck.sanity_check(); // because we can all use one

//        Card testCard = new Card(Card.suites.SPADES,Card.ranks.two);

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

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

        player1.print();
        System.out.println();
        computer.print();

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}
