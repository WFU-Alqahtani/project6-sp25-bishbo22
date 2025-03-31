import java.util.Random;

// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1,r2); // swap cards at these indices
        }
    }

    //remove a card from a specific index
    public Card remove_from_index(int index) {
        Node curr = head;

        //find the card that is being removed
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        size--; //update the size

        //index at the head
        if(curr == head){
            head = head.next;
            head.prev = null;
            return curr.data;
        }

        //index at the tail
        else if (curr == tail){
            curr.prev.next = null;
            tail = curr.prev;
            return curr.data;
        }

        //index anywhere else in the deck
        else{
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            return curr.data;
        }
    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        Node curr = head;
        Node added = new Node(x);
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        size++; //update the size accordingly for an addition to the deck

        //index at the head
        if(curr == head){
            curr.prev = added;
            added.next = curr;
            head = added;
        }

        //index at the tail
        else if(curr == tail){
            curr.prev.next = added;
            added.prev = curr.prev;
            added.next = curr;
            curr.prev = added;
        }

        //index anywhere else
        else{
            curr.prev.next = added;
            added.next = curr;
            added.prev = curr.prev;
            curr.prev = added;
        }
    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {

        //swap the cards if the first index is smaller than the second index
        if (index1 < index2) {

            //special case for the first and second indices because of how the code is written
            if (index1 == 0 && index2 == 1){
                insert_at_index(remove_from_index(index1),index2);
                return;
            }
            insert_at_index(remove_from_index(index1),index2-1);
            insert_at_index(remove_from_index(index2),index1);
        }

        //swap the cards if the second index is smaller than the first index
        else if (index2 < index1) {

            //special case for the first and second indices because of how the code is written
            if (index2 == 0 && index1 == 1){
                insert_at_index(remove_from_index(index2),index1);
                return;
            }
            insert_at_index(remove_from_index(index2),index1-1);
            insert_at_index(remove_from_index(index1),index2);
        }
    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        Node added = new Node(data);

        //if head is empty make the added card the new head
        if (head == null){
            head = added;
            head.next = tail;
        }

        //if the tail is empty and the head is full then point the tail to the added card and update tail accordingly
        else if (tail == null) {
            head.next = added;
            added.prev = head;
            tail = added;
        }

        //adding a card without head or tail being null
        else {
            tail.next = added;
            added.prev = tail;
            tail = added;
        }
        size++; //update the size
    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        Node curr = head;

        //remove the head even if it is the only card left in the hand/deck
        if (head.next == null){
            head = null;
            return curr.data;
        }
        head = head.next;
        head.prev = null;
        return curr.data;
    }

    // check to make sure the linked list is implemented correctly by iterating forwards and backwards
    // and verifying that the size of the list is the same when counted both ways.
    // 1) if a node is incorrectly removed
    // 2) and head and tail are correctly updated
    // 3) each node's prev and next elements are correctly updated
    public void sanity_check() {
        // count nodes, counting forward
        Node curr = head;
        int count_forward = 0;
        while (curr != null) {
            curr = curr.next;
            count_forward++;
        }

        // count nodes, counting backward
        curr = tail;
        int count_backward = 0;
        while (curr != null) {
            curr = curr.prev;
            count_backward++;
        }

        // check that forward count, backward count, and internal size of the list match
        if (count_backward == count_forward && count_backward == size) {
            System.out.println("Basic sanity Checks passed");
        }
        else {
            // there was an error, here are the stats
            System.out.println("Count forward:  " + count_forward);
            System.out.println("Count backward: " + count_backward);
            System.out.println("Size of LL:     " + size);
            System.out.println("Sanity checks failed");
            System.exit(-1);
        }
    }

    // print the deck
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            curr.data.print_card();
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i = i + 1;
            curr = curr.next;
        }
        System.out.println("");
    }
}