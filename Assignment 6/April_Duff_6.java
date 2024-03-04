/* Homework 6, April Duff */
import java.util.*;

/* class for creating a stack */
class Stack {
    Node top;   // points to top node of stack
    public Stack() {
        this.top = null;
    }
    // adds a node to the top of the stack
    void push(char el) {
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
    char topEl() {
        return top.value;
    }
    // returns true if stack is empty
    boolean isEmpty() {
        return top == null;
    }
    // depth-first search (find all nodes that can be reached from letter)
    void DFS(char letter, char[][] adjacency) {
        Stack s = new Stack();
        String output = "";
        ArrayList<Character> visited = new ArrayList<Character>();
        s.push(letter);
        visited.add(letter);
        output += (s.topEl() + ", ");
        s.pop();
        do {            // while stack is not empty
            for (int i = 0; i < 9; i++) {
                if (adjacency[i][0] == letter) {
                    for (int j = 1; j < adjacency[i].length; j++) {   // go through all neighbors of letter
                        if (!(visited.contains(adjacency[i][j]))) {     // if node has not been visited yet
                            s.push(adjacency[i][j]);    // push nonvisited neighbors to stack
                            visited.add(adjacency[i][j]);   // add neighbor to visited list
                        }
                    }
                    break;      // break once neighbors of letter are found
                }
            }
            if (!s.isEmpty()) {
                letter = s.topEl();
            }
            output += (s.topEl() + ", ");   // print top element of stack
            s.pop();    // pop first element of stack
        } while (!(s.isEmpty()));
        System.out.println(output.substring(0, output.length() - 2));
    }
}
/* class for creating a queue */
class Queue {
    int front, rear;   // points to the front and rear of queue
    int size = 100;
    char[] arr = new char[size];
    Queue() {
        this.front = 0;
        this.rear = 0;
    }
    // add node to queue
    void enqueue(char el) {
        if (!isFull()) {        // if queue is not empty
            if (rear == size-1 || rear == -1) {
                arr[0] = el;
                rear = 0;
                if (front == -1) {      // if queue is empty before enqueue
                    front = 0;
                }
            } else {
                arr[rear++] = el;
            }
        }
    }
    // removes a node from queue
    void dequeue() {
        if (front == rear) {    // if there's one element
            rear = front = 0;
        } else if (front == size - 1) {     // if the queue is empty
            front = 0;
        } else {
            front++;
        }
    }
    boolean isFull() {
        return front == 0 && rear == size - 1 || front == rear + 1;
    }
    // breadth-first search (print the minimum path from start to destination)
    void BFS(char start, char destination, char[][] adjacency) {
        ArrayList<Character> visited = new ArrayList<Character>();
        ArrayList<Character> origin = new ArrayList<Character>();
        ArrayList<Character> queue = new ArrayList<Character>();
        int front = 0;
        int rear = 0;
        origin.add('\0');
        queue.add(start);
        do {
            for (int i = 0; i < 9; i++) {
                if (adjacency[i][0] == start) {
                    for (int j = 1; j < adjacency[i].length; j++) {   // go through all neighbors of start letter
                        if (!(visited.contains(adjacency[i][j]))) {     // if cell has not been visited yet
                            rear++;
                            queue.add(adjacency[i][j]);  // enqueue neighbor of start
                            origin.add(start);  // add start as the origin of its neighbor
                            visited.add(adjacency[i][j]);   // add neighbor to visited list
                        }
                    }
                    break;      // break once neighbors of letter are found
                }
            }
            front++;
            start = queue.get(front);
        } while (queue.get(rear) != destination);
        String output = destination + " >-- ";
        do {
            output += (origin.get(rear) + " >-- ");
            for (int x = 0; x < queue.size(); x++) {
                if (queue.get(x) == origin.get(rear)) {
                    rear = x;
                    break;
                }
            }
        } while (origin.get(rear) != queue.get(0));
        output += (queue.get(0));
        for (int s = output.length() - 1; s >= 0; s--) {
            System.out.print(output.charAt(s));
        }
        System.out.println();
    }
}
/* class for creating a node */
class Node {
    Node link;
    char value;
    Node() {
        this.link = null;
    }
    Node (char value) {
        this.value = value;
        this.link = null;
    }
}
public class April_Duff_6 {
    static void printMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("                     M E N U                      ");
        System.out.println(" Depth-First Search (0), Minimum Path Search (1), ");
        System.out.println("                 Exit Program (3)                 ");
        System.out.println("                     Choose?                      ");
        System.out.println("--------------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printMenu();
        Stack stack = new Stack();
        Queue queue = new Queue();
        char[][] adjacency = {{'A', 'B', 'C', 'D'}, {'B', 'E'}, {'C', 'B', 'G'}, {'D', 'C', 'G'},
                        {'E', 'C', 'F'}, {'F', 'C', 'H'}, {'G', 'F', 'H', 'I'}, {'H', 'E', 'I'}, {'I', 'F'}};
        try {
            int choice = sc.nextInt();
            while (choice != 3) {
                switch(choice) {
                    case 0:
                        stack.DFS(sc.next().charAt(0), adjacency);
                        break;
                    case 1:
                        queue.BFS(sc.next().charAt(0), sc.next().charAt(0), adjacency);
                        break;
                }
                printMenu();
                choice = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter your choice (number) with a space and a letter.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}