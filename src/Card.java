// Standard French-style cards
public class Card {

    // Suites
    public enum suites {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace
    }

    private suites suit;
    private ranks rank;

    Card(){
        suit = suites.NULL;
        rank = ranks.NULL;
    }

    Card(suites s, ranks r){
        suit = s;
        rank = r;
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }

    public int compareTo(Card other) {
        if (this.rank.ordinal() > other.rank.ordinal()) {
            return 1;
        } else if (this.rank.ordinal() < other.rank.ordinal()) {
            return -1;
        } else { // Ranks are equal, compare suits
            return Integer.compare(this.suit.ordinal(), other.suit.ordinal());
        }
    }
}
