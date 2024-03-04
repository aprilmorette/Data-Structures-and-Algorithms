/* Homework #4, April Duff */

import java.util.*;

class Grid {
    int horizontal, vertical;
    List<Integer> route = new ArrayList<>();
    /* grid constructor */
    Grid() {
        this.horizontal = 0;
        this.vertical = 0;
    }
    /* add horizontal axis */
    void addHorizontal(int axis) {
        this.horizontal = axis;
    }
    /* add vertical axis */
    void addVertical(int axis) {
        this.vertical = axis;
    }
    /* create and populate grid */
    int[][] createGrid() {
        int count = 1;
        int[][] grid = new int[this.horizontal][this.vertical];
        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                grid[i][j] = count;
                count++;
            }
        }
        return grid;
    }
    /* find all possible routes in the grid */
    void startDiscovery(int[][] arr, int row, int col) {
        if (row == horizontal - 1 && col == vertical - 1) {  // if the bottom-right cell
            route.add(arr[row][col]);
            System.out.println(route.toString());
            route.remove(route.size() - 1);
            return;
        }
        if (row < 0 || row >= horizontal || col < 0 || col >= vertical) {   // if out of bounds
            return;
        }
        route.add(arr[row][col]);    // add the grid value to current route
        if (row + 1 < horizontal) {     // move right
            startDiscovery(arr, row + 1, col);
        }
        if (col + 1 < vertical) {       // move left
            startDiscovery(arr, row, col + 1);
        }
        if (row + 1 < horizontal && col + 1 < vertical) {       // move diagonally (right and down)
            startDiscovery(arr, row + 1, col + 1);
        }
        route.remove(route.size() - 1);   // clear route
    }
}

public class April_Duff_4 {
    static void printMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("                     M E N U                   ");
        System.out.println("     Horizontal Axis (0), Vertical Axis (1),   ");
        System.out.println("      Start Discovery (2), Exit Program (3)    ");
        System.out.println("                     Choose?                   ");
        System.out.println("-----------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grid grid = new Grid();
        printMenu();
        try {
            int choice = sc.nextInt();
            while (choice != 3) {
                switch(choice) {
                    case 0:
                        grid.addHorizontal(sc.nextInt());
                        break;
                    case 1:
                        grid.addVertical(sc.nextInt());
                        break;
                    case 2: 
                        grid.startDiscovery(grid.createGrid(), 0, 0);
                        break;
                }
                printMenu();
                choice = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format");
            System.out.println("For choices 0 and 1: Enter the choice followed by a space and an integer.");
            System.out.println("For choices 2 and 3: Enter the choice");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
