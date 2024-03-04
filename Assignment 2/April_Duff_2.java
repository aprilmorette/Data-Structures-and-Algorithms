/* Homework #2, April Duff */
import java.util.*;

/* Single Linked List class */
class SLList {
    SLLNode head, tail;
    public SLList()  {
        this.head = null;
        this.tail = null;
    }
    void insertHead(char el) {   // add node to front (SLL)
        head = new SLLNode(el, head);
        if (tail == null) {
            tail = head;
        }
    }
    void insertTail(char el) {   // add node to end (SLL)
        if (tail != null) {
            tail.next = new SLLNode(el);
            tail = tail.next;
        } else {
            head = tail = new SLLNode(el);
        }
    }
    char deleteHead() {     // delete first node (SLL)
        char value = head.value;
        if (head == tail) {     // if there's one node
            head = tail = null;
        } else {        // if there's more than one node
            SLLNode temp = head;    
            head = head.next;
            temp = null;
        }
        return value;
    }
    char deleteTail() {     // delete last node (SLL)
        char value = tail.value;
        if (head == tail) {     // if there's one node
            head = tail = null;
        } else {        // if there's more than one node
            SLLNode temp = head;
            while(temp != null) {
                if (temp.next == tail) {
                    tail = null;
                    tail = temp;
                    tail.next = null;
                }
                temp = temp.next;
            }
        }
        return value;
    }
    void searchAndDelete(char el) {       // search and delete node (SLL)
        if (head != null) {        // if list is not empty
            if (head == tail && el == head.value) {     // if there's one node
                head = tail = null;
            } else if (el == head.value) {      // if target node is head
                deleteHead();
            } else if (el == tail.value) {      // if target node is tail
                deleteTail();
            } else {        // if target node is between head and tail
                SLLNode pred = head; 
                SLLNode temp = head.next;
                boolean found = false;
                while(temp != null) {
                    if (temp.value == el) {
                        pred.next = temp.next;
                        temp = null;
                        found = true;
                        break;
                    }
                    pred = pred.next;
                    temp = temp.next;
                }
                if (found == false) {       // if target node is not in the list
                    System.out.println("There is no node with the value " + el + " in the single linked list.");
                }
            }
        } else {    // if list is empty
            System.out.println("Single linked list is currently empty.");
        }
    }
    void printSLL() {       // print single linked list
        SLLNode temp = head;
        System.out.print("Single Linked List: ");
        while(temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
/* Single Linked List Node class */
class SLLNode {
    SLLNode next;
    char value;
    SLLNode() {
        this.next = null;
    }
    SLLNode(char value) {
        this.value = value;
    }
    SLLNode(char value, SLLNode next) {
        this.value = value;
        this.next = next;
    }
}
/* Double Linked List class */
class DLList {
    DLLNode head, tail;
    public DLList() {
        this.head = null;
        this.tail = null;
    }
    void insertHead(char el) {      // add node to front (DLL)
        head = new DLLNode(el, head, null);
        if (tail == null) {
            tail = head;
        }
    }
    void insertTail(char el) {      // add node to end (DLL)
        if (tail != null) {
            tail.next = new DLLNode(el, null, tail);
            tail = tail.next;
        } else {
            head = tail = new DLLNode(el, null, null);
        }
    }
    char deleteHead() {     // delete first node (DLL)
        char value = head.value;
        if (head == tail) {     // if there's one node
            head = tail = null;
        } else {        // if there's more than one node
            head = head.next;
            head.previous = null;
        }
        return value;
    }
    char deleteTail() {     // delete last node (DLL)
        char value = tail.value;
        if (head == tail) {     // if there's one node
            head = tail = null;
        } else {        // if there's more than one node
            tail = tail.previous;
            tail.next = null;
        }
        return value;
    }
    void searchAndDelete(char el) {       // search and delete node (DLL)
        if (head != null) {     // if list is not empty
            if (head == tail && el == head.value) {     // if there's one node
                head = tail = null;
            } else if (el == head.value) {      // if target node is head
                deleteHead();  
            } else if (el == tail.value) {      // if target node is tail
                deleteTail();
            } else {        // if target node is between head and tail
                DLLNode pred = head;
                DLLNode temp = head.next;
                boolean found = false;
                while (temp != null) {
                    if (temp.value == el) {
                        pred.next = temp.next;
                        temp.next.previous = pred;
                        temp = null;
                        found = true;
                        break;
                    }
                    pred = pred.next;
                    temp = temp.next;
                }
                if (found = false) {        // if target node is not in the list
                    System.out.println("There is no node with the value " + el + " in the double linked list.");
                }
            }
        } else {    // if list is empty
            System.out.println("Double linked list is currently empty.");
        }
    }
    void printDLL() {       // print double linked list
        DLLNode temp = head;
        System.out.print("Double Linked List: ");
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
/* Double Linked List Node class */
class DLLNode {
    DLLNode next;
    DLLNode previous;
    char value;
    DLLNode() {
        this.next = null;
        this.previous = null;
    }
    DLLNode(char value, DLLNode next, DLLNode previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}

public class April_Duff_2 {
    static void printMenu() {
        /* IH (Insert Head), IT (Insert Tail), DH (Delete Head), DT (Delete Tail)
           SD (Search & Delete), PS (Print Single Linked List), PD (Print Double Linked List) */
        System.out.println("-----------------------------------------------");
        System.out.println("                     M E N U                   ");
        System.out.println("SLL: IH(0), IT(1), DH(2), DT(3), SD(4), PS(5)");
        System.out.println("DLL: IH(6), IT(7), DH(8), DT(9), SD(10), PD(11)");
        System.out.println("Exit Program (12)");
        System.out.println("                     Choose?                   ");
        System.out.println("-----------------------------------------------");
    }
    public static void main(String[] args) {
        SLList singleLinkedList = new SLList();
        DLList doubleLinkedList = new DLList();
        printMenu();
        Scanner sc = new Scanner(System.in);
        try {
            int choice = sc.nextInt();
            while (choice != 12) {
                switch(choice) {
                    case 0:
                        singleLinkedList.insertHead((sc.nextLine()).charAt(1));
                        break;
                    case 1:
                        singleLinkedList.insertTail((sc.nextLine()).charAt(1));
                        break;
                    case 2:
                        singleLinkedList.deleteHead();
                        break;
                    case 3:
                        singleLinkedList.deleteTail();
                        break;
                    case 4:
                        singleLinkedList.searchAndDelete((sc.nextLine()).charAt(1));
                        break;
                    case 5:
                        singleLinkedList.printSLL();
                        break;
                    case 6:
                        doubleLinkedList.insertHead((sc.nextLine()).charAt(1));
                        break;
                    case 7:
                        doubleLinkedList.insertTail((sc.nextLine()).charAt(1));
                        break;
                    case 8:
                        doubleLinkedList.deleteHead();
                        break;
                    case 9:
                        doubleLinkedList.deleteTail();
                        break;
                    case 10:
                        doubleLinkedList.searchAndDelete((sc.nextLine()).charAt(1));
                        break;
                    case 11:
                        doubleLinkedList.printDLL();
                        break;
                    }
                printMenu();
                choice = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please use the following format");
            System.out.println("For inserting and searching: option value");
            System.out.println("For deleting and printing: option");
        } catch (NullPointerException e) {
            System.out.println("The list is currently empty.");
        }
    }
}