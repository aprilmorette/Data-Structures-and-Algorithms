/* Homework #3, April Duff */
import java.util.*; 

/* class for creating a stack implemented by a linked list */
class Stack {
    Node top;   // points to top node of stack
    public Stack() {
        this.top = null;
    }
    // adds a node to the top of the stack
    void push(int el) {
        if (top == null) {      // if stack is empty
            top = new Node(el);
        } else {        // otherwise
            Node temp = new Node(el);
            temp.link = top;
            top = temp;
        }
    }
    // removes the top node
    void pop() {
        if (top.link == null) {      // if stack has one node
            top = null;
        } else {        // if stack has two or more nodes
            top = top.link;
        }
    }
    // returns top node value without removing it
    void topEl() {
        System.out.println(top.value);
    }
    // returns true if stack is empty
    boolean isEmpty() {
        return top == null;
    }
    // converts input to binary
    void binary(String input) {
        int number = Integer.parseInt(input);
        Stack binaryStack = new Stack();
        int remainder;
        while(number != 0) {
            remainder = number % 2;
            binaryStack.push(remainder);
            number /= 2;
        }
        // prints binary stack
        Node temp = binaryStack.top;
        System.out.print("Binary: ");
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.link;
        }
        System.out.println();
    }
    // converts input to octal
    void octal(String input) {
        int number = Integer.parseInt(input);
        Stack octalStack = new Stack();
        int remainder;
        if (number < 8) {
            octalStack.push(number);
        } else {
            while (number != 0) {
                remainder = number % 8;
                octalStack.push(remainder);
                number /= 8;
            }
        }
        // prints octal stack
        Node temp = octalStack.top;
        System.out.print("Octal: ");
        while(temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.link;
        }
        System.out.println();
    }
    // converts input to hexadecimal
    void hex(String input) {
        int number = Integer.parseInt(input);
        Stack hexStack = new Stack();
        int remainder;
        if (number < 10) {
            hexStack.push(number);
        } else {
            while (number != 0) {
                if (number > 9 && number < 16) {
                    switch(number) {
                        case 10:
                            hexStack.push('A');
                            break;
                        case 11:
                            hexStack.push('B');
                            break;
                        case 12:
                            hexStack.push('C');
                            break;
                        case 13:
                            hexStack.push('D');
                            break;
                        case 14:
                            hexStack.push('E');
                            break;
                        case 15:
                            hexStack.push('F');
                            break;
                    }
                } else {
                    remainder = number % 16;
                    hexStack.push(remainder);
                }
                number /= 16;
            }
        }
        // print hexadecimal stack
        Node temp = hexStack.top;
        System.out.print("Hexadecimal: ");
        while(temp != null) {
            switch(temp.value) {
                case 10:
                    System.out.print("A ");
                    break;
                case 11:
                    System.out.print("B ");
                    break;
                case 12:
                    System.out.print("C");
                    break;
                case 13:
                    System.out.print("D ");
                    break;
                case 14:
                    System.out.print("E ");
                    break;
                case 15: 
                    System.out.print("F ");
                    break;
            }
            if (temp.value < 10) {
                System.out.print(temp.value + " ");
            }
            temp = temp.link;
        }
        System.out.println();
    }
}
/* class for creating a node */
class Node {
    Node link;
    int value;
    Node() {
        this.link = null;
    }
    Node (int el) {
        this.value = el;
    }
}

public class April_Duff_3 {
    static void printMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("                     M E N U                   ");
        System.out.println("     Binary (0), Octal (1), Hexadecimal (2)    ");
        System.out.println("               Exit Program (3)                ");
        System.out.println("                     Choose?                   ");
        System.out.println("-----------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack();
        printMenu();
        try {
            int choice = sc.nextInt();
            while (choice != 3) {
                switch(choice) {
                    case 0:
                        stack.binary(sc.next());
                        break;
                    case 1:
                        stack.octal(sc.next());
                        break;
                    case 2:
                        stack.hex(sc.next());
                        break;
                }
                printMenu();
                choice = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter your choice and the number you are wanting to convert.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}